package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.imbuement.ImbuementDTO;
import com.tibiainfo.model.dto.query.ImbuementQueryDTO;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImbuementServiceTest {

    private final Long EXISTING_IMBUEMENT = 80101L;
    private final Long NON_EXISTING_IMBUEMENT = -1L;

    private final String IMBUEMENT_NAME = "Powerful Venom";

    private final ImbuementQueryDTO.ImbuementQueryDTOBuilder<?, ?> IMBUEMENT_QUERY_DTO_BUILDER;
    @Autowired
    private ImbuementService imbuementService;

    {
        IMBUEMENT_QUERY_DTO_BUILDER = ImbuementQueryDTO.builder()
                .page(0)
                .size(10);
    }

    @Test
    public void testGetImbuement() {
        PageSupportDTO<ImbuementDTO> imbuements = imbuementService.getImbuements(IMBUEMENT_QUERY_DTO_BUILDER.build());

        assertNotNull(imbuements);
        assertNotNull(imbuements.getContent());
        assertNotNull(imbuements.getNumberOfElements());
        assertNotNull(imbuements.getTotalElements());
        assertNotNull(imbuements.getTotalPages());

    }

    @Test
    public void testGetImbumentsByName() {
        ImbuementQueryDTO imbuementQueryDTO = ImbuementQueryDTO.builder()
                .name(Optional.of(IMBUEMENT_NAME))
                .page(0)
                .size(10)
                .build();

        PageSupportDTO<ImbuementDTO> imbuements = imbuementService.getImbuements(
                IMBUEMENT_QUERY_DTO_BUILDER.name(Optional.of(IMBUEMENT_NAME)).extended(true).build()
        );

        assertNotNull(imbuements);
        assertNotNull(imbuements.getContent());
        assertFalse(imbuements.isEmpty());
        assertTrue(
                imbuements.getContent()
                        .stream()
                        .allMatch(imbuement -> nonNull(imbuement.getName()) && imbuement.getName().equalsIgnoreCase(IMBUEMENT_NAME))
        );
    }

    @Test
    public void testGetImbuementById() throws NotFoundException {
        ImbuementDTO fetchedImbuement = imbuementService.getImbuementById(EXISTING_IMBUEMENT);

        assertNotNull(fetchedImbuement);
        assertEquals(EXISTING_IMBUEMENT, fetchedImbuement.getId());
    }

    @Test(expected = NotFoundException.class)
    public void testGetImbuementDoesNotExist() throws NotFoundException {
        imbuementService.getImbuementById(NON_EXISTING_IMBUEMENT);
    }

    @Test
    public void testGetImbuementImage() throws NotFoundException {
        byte[] image = imbuementService.getImage(EXISTING_IMBUEMENT);

        assertNotNull(image);
    }

    @Test(expected = NotFoundException.class)
    public void testGetImageForAnImbuementThatDoesNotExist() throws NotFoundException {
        imbuementService.getImage(NON_EXISTING_IMBUEMENT);
    }

    @Test
    public void testGetImbuementByName() {
        imbuementService.getImbuements(
                IMBUEMENT_QUERY_DTO_BUILDER.name(Optional.of(IMBUEMENT_NAME))
                        .name(Optional.of("Powerful Venm"))
                        .build()
        );
    }

    @Test
    public void testGetImbuementWithNonExtendedJson() {
        PageSupportDTO<ImbuementDTO> imbuements = imbuementService.getImbuements(
                IMBUEMENT_QUERY_DTO_BUILDER.build()
        );
        assertNotNull(imbuements);
        assertNotNull(imbuements.getContent());
        assertFalse(imbuements.isEmpty());
        assertTrue(
                imbuements.getContent()
                        .stream()
                        .map(ImbuementDTO::getEffect)
                        .allMatch(Objects::isNull)
        );
    }

    @Test
    public void testImbuementWithExtendedJson() {
        PageSupportDTO<ImbuementDTO> imbuements = imbuementService.getImbuements(
                IMBUEMENT_QUERY_DTO_BUILDER.extended(true).build()
        );

        assertNotNull(imbuements);
        assertNotNull(imbuements.getContent());
        assertFalse(imbuements.isEmpty());
        assertTrue(
                imbuements.getContent()
                        .stream()
                        .map(ImbuementDTO::getEffect)
                        .anyMatch(Objects::nonNull)
        );
    }


}
