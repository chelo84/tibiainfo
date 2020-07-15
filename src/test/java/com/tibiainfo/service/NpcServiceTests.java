package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.npc.NpcDTO;
import com.tibiainfo.model.dto.query.NpcQueryDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NpcServiceTests {

    private final Long EXISTING_NPC = 1111L;

    private final Long NON_EXISTING_NPC = -1L;

    private final String CITY_ROOKGUARD = "Rookgaard";

    private final NpcQueryDTO.NpcQueryDTOBuilder<?, ?> QUERY_DTO_BUILDER;

    @Autowired
    private NpcService npcService;

    {
        QUERY_DTO_BUILDER = NpcQueryDTO.builder()
                .page(0)
                .size(10);
    }

    @Test
    public void testGetNpcs() {
        PageSupportDTO<NpcDTO> npcs = npcService.getNpcs(QUERY_DTO_BUILDER.build());

        assertNotNull(npcs);
        assertNotNull(npcs.getContent());
        assertNotNull(npcs.getNumberOfElements());
        assertNotNull(npcs.getTotalElements());
        assertNotNull(npcs.getTotalPages());

        assertFalse(npcs.isEmpty());
        assertFalse(npcs.getContent().isEmpty());
        assertFalse(npcs.isLast());
    }

    @Test
    public void testGetItemsOfType() {
        PageSupportDTO<NpcDTO> npcs = npcService.getNpcs(
                QUERY_DTO_BUILDER.city(Optional.of(CITY_ROOKGUARD)).build()
        );

        assertNotNull(npcs);
        assertNotNull(npcs.getContent());
        assertFalse(npcs.isEmpty());
        assertTrue(
                npcs.getContent()
                        .stream()
                        .allMatch(item -> item.getCity().equalsIgnoreCase(CITY_ROOKGUARD))
        );
    }

    @Test
    public void testGetNpcById() throws NotFoundException {
        NpcDTO fetchedItem = npcService.getNpcById(EXISTING_NPC);

        assertNotNull(fetchedItem);
        assertEquals(EXISTING_NPC, fetchedItem.getId());
    }

    @Test(expected = NotFoundException.class)
    public void testGetNpcThatDoesNotExist() throws NotFoundException {
        npcService.getNpcById(NON_EXISTING_NPC);
    }

    @Test
    public void testGetNpcImage() throws NotFoundException {
        byte[] image = npcService.getImage(EXISTING_NPC);

        assertNotNull(image);
    }

    @Test(expected = NotFoundException.class)
    public void testGetItemImageForAnNpcThatDoesNotExist() throws NotFoundException {
        npcService.getImage(NON_EXISTING_NPC);
    }

    @Test
    public void testGetNpcByNameAndCity() {
        npcService.getNpcs(
                QUERY_DTO_BUILDER.city(Optional.of(CITY_ROOKGUARD))
                        .name(Optional.of("Odiii"))
                        .build()
        );
    }

    @Test
    public void testGetNpcWithNonExtendedJson() {
        PageSupportDTO<NpcDTO> npcs = npcService.getNpcs(
                QUERY_DTO_BUILDER.build()
        );

        assertNotNull(npcs);
        assertNotNull(npcs.getContent());
        assertFalse(npcs.isEmpty());
        assertTrue(
                npcs.getContent()
                        .stream()
                        .map(NpcDTO::getJob)
                        .allMatch(Objects::isNull)
        );
    }

    @Test
    public void testGetNpcsWithExtendedJson() {
        PageSupportDTO<NpcDTO> npcs = npcService.getNpcs(
                QUERY_DTO_BUILDER.extended(true).build()
        );

        assertNotNull(npcs);
        assertNotNull(npcs.getContent());
        assertFalse(npcs.isEmpty());
        assertTrue(
                npcs.getContent()
                        .stream()
                        .map(NpcDTO::getId)
                        .anyMatch(Objects::nonNull)
        );
    }
}
