package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.CreatureQueryDTO;
import com.tibiainfo.model.dto.CreatureQueryDTO.CreatureQueryDTOBuilder;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.creature.CreatureDTO;
import com.tibiainfo.model.dto.creature.CreatureDropDTO;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CreatureServiceTest {

    private final Long EXISTING_CREATURE = 1150L;
    private final Long NON_EXISTING_CREATURE = -1L;

    private final String CREATURE_NAME = "Dog";

    private final CreatureQueryDTOBuilder<?, ?> CREATURE_QUERY_DTO_BUILDER;
    @Autowired
    private CreatureService creatureService;

    {
        CREATURE_QUERY_DTO_BUILDER = CreatureQueryDTO.builder()
                .page(0)
                .size(10);
    }

    @Test
    public void testGetCreature() {
        PageSupportDTO<CreatureDTO> creatures = creatureService.getCreatures(CREATURE_QUERY_DTO_BUILDER.build());

        assertNotNull(creatures);
        assertNotNull(creatures.getContent());
        assertNotNull(creatures.getNumberOfElements());
        assertNotNull(creatures.getTotalElements());
        assertNotNull(creatures.getTotalPages());

        assertFalse(creatures.isEmpty());
        assertFalse(creatures.getContent().isEmpty());
        assertFalse(creatures.isLast());
    }

    @Test
    public void testGetCreatureByName() {
        CreatureQueryDTO creatureQueryDto = CreatureQueryDTO.builder()
                .name(Optional.of(CREATURE_NAME))
                .page(0)
                .size(10)
                .build();

        PageSupportDTO<CreatureDTO> creatures = creatureService.getCreatures(
                CREATURE_QUERY_DTO_BUILDER.name(Optional.of(CREATURE_NAME)).build()
        );

        assertNotNull(creatures);
        assertNotNull(creatures.getContent());
        assertFalse(creatures.isEmpty());
        assertTrue(
                creatures.getContent()
                        .stream()
                        .allMatch(creature -> creature.getName().equalsIgnoreCase(CREATURE_NAME))
        );

    }

    @Test
    public void testGetCreatureById() throws NotFoundException {
        CreatureDTO fetchedCreature = creatureService.getCreatureById(EXISTING_CREATURE);

        assertNotNull(fetchedCreature);
        assertEquals(EXISTING_CREATURE, fetchedCreature.getId());
    }

    @Test(expected = NotFoundException.class)
    public void testGetCreatureThatDoesExist() throws NotFoundException {
        creatureService.getCreatureById(NON_EXISTING_CREATURE);
    }

    @Test
    public void testGetCreatureImage() throws NotFoundException {
        byte[] image = creatureService.getImage(EXISTING_CREATURE);

        assertNotNull(image);
    }

    @Test(expected = NotFoundException.class)
    public void testGetCreatureImageForACreatureThatDoesNotExist() throws NotFoundException {
        creatureService.getImage(NON_EXISTING_CREATURE);
    }

}
