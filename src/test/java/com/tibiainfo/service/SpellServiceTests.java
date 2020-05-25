package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.query.SpellQueryDTO;
import com.tibiainfo.model.dto.spell.SpellDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpellServiceTests {

    private final Long EXISTING_SPELL = 795L;
    private final Long NON_EXISTING_SPELL = -1L;

    private final String INSTANT_TYPE = "Instant";

    private final SpellQueryDTO.SpellQueryDTOBuilder<?, ?> QUERY_DTO_BUILDER;
    @Autowired
    private SpellService spellService;

    {
        QUERY_DTO_BUILDER = SpellQueryDTO.builder()
                .page(0)
                .size(10);
    }

    @Test
    public void testGetSpells() {
        PageSupportDTO<SpellDTO> spells = spellService.getSpells(QUERY_DTO_BUILDER.build());

        assertNotNull(spells);
        assertNotNull(spells.getContent());
        assertNotNull(spells.getNumberOfElements());
        assertNotNull(spells.getTotalElements());
        assertNotNull(spells.getTotalPages());

        assertFalse(spells.isEmpty());
        assertFalse(spells.getContent().isEmpty());
        assertFalse(spells.isLast());
    }

    @Test
    public void testGetSpellsOfType() {
        PageSupportDTO<SpellDTO> spells = spellService.getSpells(
                QUERY_DTO_BUILDER.type(Optional.of(INSTANT_TYPE)).extended(true).build()
        );

        assertNotNull(spells);
        assertNotNull(spells.getContent());
        assertFalse(spells.isEmpty());
        assertTrue(
                spells.getContent()
                        .stream()
                        .allMatch(spell -> spell.getType().equalsIgnoreCase(INSTANT_TYPE))
        );
    }

    @Test
    public void testGetSpellById() throws NotFoundException {
        SpellDTO fetchedSpell = spellService.getSpellById(EXISTING_SPELL);

        assertNotNull(fetchedSpell);
        assertEquals(EXISTING_SPELL, fetchedSpell.getId());
    }

    @Test
    public void testGetSpellImage() throws NotFoundException {
        byte[] image = spellService.getImage(EXISTING_SPELL);

        assertNotNull(image);
    }

    @Test(expected = NotFoundException.class)
    public void testGetSpellImageForAnSpellThatDoesNotExist() throws NotFoundException {
        spellService.getImage(NON_EXISTING_SPELL);
    }

    @Test
    public void testGetSpellByType() {
        spellService.getSpells(
                QUERY_DTO_BUILDER.type(Optional.of(INSTANT_TYPE))
                        .name(Optional.of("Instantee"))
                        .build()
        );
    }

    @Test
    public void testGetSpellsWithNonExtendedJson() {
        PageSupportDTO<SpellDTO> spells = spellService.getSpells(
                QUERY_DTO_BUILDER.build()
        );

        assertNotNull(spells);
        assertNotNull(spells.getContent());
        assertFalse(spells.isEmpty());
        assertTrue(
                spells.getContent()
                        .stream()
                        .map(SpellDTO::getEffect)
                        .allMatch(Objects::isNull)
        );
    }

    @Test
    public void testGetSpellsWithExtendedJson() {
        PageSupportDTO<SpellDTO> spells = spellService.getSpells(
                QUERY_DTO_BUILDER.extended(true).build()
        );

        assertNotNull(spells);
        assertNotNull(spells.getContent());
        assertFalse(spells.isEmpty());
        assertTrue(
                spells.getContent()
                        .stream()
                        .map(SpellDTO::getKnight)
                        .anyMatch(Objects::nonNull)
        );
    }
}
