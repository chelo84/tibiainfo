package com.tibiainfo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

public class RoutingDataSource extends AbstractRoutingDataSource {

    public static final int DEFAULT = 0;
    public static final int NEW = 1;

    private volatile int key = DEFAULT;

    @Setter
    @Getter
    private volatile String filename = null;
    private Map<Object, Object> dataSources = new HashMap();

    RoutingDataSource() {
        setTargetDataSources(dataSources);
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void addDataSource(int key, DataSource dataSource) {
        DataSource overridenDataSource = (DataSource) dataSources.put(key, dataSource);

        if (nonNull(overridenDataSource)) {
            try {
                overridenDataSource.getConnection().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return key;
    }

    @Override
    protected DataSource determineTargetDataSource() {
        return (DataSource) dataSources.get(key);
    }
}