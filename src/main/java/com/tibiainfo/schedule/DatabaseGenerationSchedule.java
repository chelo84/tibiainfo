package com.tibiainfo.schedule;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.tibiainfo.config.RoutingDataSource;
import com.tibiainfo.exception.PipInstallationException;
import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.Objects.nonNull;

@Slf4j
@Component
@ConditionalOnProperty(
        value = "scheduling.enable", havingValue = "true", matchIfMissing = true
)
@EnableScheduling
public class DatabaseGenerationSchedule {

    private final List<String> dbColumnsWithImages = List.of("item", "charm", "creature", "imbuement", "item", "map", "mount", "npc", "outfit_image", "spell");

    private final String IMAGES_ZIP = "images.zip",
            IMAGES_FOLDER = "images/",
            NEW_DB_NAME = "tibiainfo-%s.db",
            NEW_DB_CONNECTION_URL = "jdbc:sqlite:%s";

    @Value("${google.cloud.project.id}")
    private String projectId;
    @Value("${google.cloud.bucket.name}")
    private String bucketName;
    @Value("${driverClassName}")
    private String driverClassName;
    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String hibernateDialect;
    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Autowired
    private AbstractRoutingDataSource abstractRoutingDataSource;

    @Scheduled(fixedRateString = "${database.update.rate}", initialDelay = 2500)
    public void process() {
        if (!activeProfile.equalsIgnoreCase("dev")) {
            try {
//                log.info("--------------------- Generating database ---------------------");
//                this.downloadTibiaImages();
//
//                String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddyyyyHHmm"));
//                final Path output = Path.of(String.format("database-log-%s.txt", dateStr));
//
//                this.installTibiaWikiSqlLib(output);
//
//                final String dbFilename = String.format(NEW_DB_NAME, dateStr);
//                this.generateDatabase(output, dbFilename);
//
//                log.info("---------------- Finished generating database ----------------");
//
                changeCurrentDatabase("tibiainfo2.db", "jdbc:sqlite:tibiainfo2.db");

            } catch (Exception ex) {
                log.error(ex.getMessage(), ex);

                log.info("Something gone wrong while generating database");
            }
        }
    }

    private void downloadTibiaImages() throws ZipException {
        if (!Path.of(IMAGES_FOLDER).toFile().exists()) {
            log.info("Downloading images from Google Cloud bucket...");
            this.downloadImagesFromBucket();

            ZipFile zipFile = new ZipFile(IMAGES_ZIP);

            log.info("Extracting images...");
            zipFile.extractAll("images/");
        }
    }

    private void downloadImagesFromBucket() {
        String objectName = "images.zip";

        if (!Path.of(objectName).toFile().exists()) {
            Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();

            Blob blob = storage.get(BlobId.of(bucketName, objectName));
            blob.downloadTo(Paths.get(IMAGES_ZIP));

            log.info("Downloaded object {} from bucket name {} to {}", objectName, bucketName, Paths.get(IMAGES_ZIP));
        }
    }

    private void installTibiaWikiSqlLib(final Path output) throws IOException, ExecutionException, InterruptedException {
        log.info("Installing TibiaWikiSQL...");

        new ProcessBuilder("pip", "install", "tibiawikisql")
                .redirectErrorStream(true)
                .redirectOutput(Redirect.to(output.toFile()))
                .redirectError(Redirect.to(output.toFile()))
                .start()
                .onExit()
                .thenApply(p -> p.exitValue() == 0)
                .get();
    }

    private void generateDatabase(final Path output, final String dbFilename) throws IOException, ExecutionException, InterruptedException {
        log.info("Generating database...");

        new ProcessBuilder("tibiawikisql", "generate", "-db", dbFilename)
                .redirectErrorStream(true)
                .redirectOutput(Redirect.to(output.toFile()))
                .redirectError(Redirect.to(output.toFile()))
                .start()
                .onExit()
                .thenApply(p -> {
                    boolean success = p.exitValue() == 0;

                    if (success) {
                        try {
                            final String newDbUrl = String.format(NEW_DB_CONNECTION_URL, dbFilename);
                            this.updateNewDatabase(newDbUrl);

                            this.changeCurrentDatabase(dbFilename, newDbUrl);
                        } catch (SQLException ex) {
                            log.error(ex.getMessage(), ex);
                        }
                    }

                    return success;
                })
                .get();
    }

    private void changeCurrentDatabase(final String dbFilename, final String newDbUrl) throws SQLException {
        log.info("Changing current database to {}...", dbFilename);
        log.info(newDbUrl);

        String previousDbFilename = null;

        if (abstractRoutingDataSource instanceof RoutingDataSource) {
            final DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(driverClassName);
            dataSource.setUrl(newDbUrl);

            RoutingDataSource rds = (RoutingDataSource) abstractRoutingDataSource;

            rds.addDataSource(RoutingDataSource.NEW, dataSource);
            rds.setKey(RoutingDataSource.NEW);

            previousDbFilename = rds.getFilename();
            rds.setFilename(dbFilename);
        }

        if (nonNull(previousDbFilename)) {
            if (Path.of(previousDbFilename).toFile().delete()) {
                log.info("Previous db file deleted");
            }
        }
    }

    private void updateNewDatabase(final String newDbUrl) throws SQLException {
        log.info("Updating new database...");
        Connection newDbConnection = null;
        try {
            newDbConnection = DriverManager.getConnection(newDbUrl);
            this.setImagesToHex(newDbConnection);
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        } finally {
            if (nonNull(newDbConnection)) newDbConnection.close();
        }
    }

    private void setImagesToHex(final Connection newDbConnection) throws SQLException {
        log.info("Setting images to hex...");

        final String imageUpdateSql = "update %s set image = hex(image) where image is not null;";

        for (String column : dbColumnsWithImages) {
            newDbConnection.prepareStatement(String.format(imageUpdateSql, column))
                    .executeUpdate();
        }
    }

}
