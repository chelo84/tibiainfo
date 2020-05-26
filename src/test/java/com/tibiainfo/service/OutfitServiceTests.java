package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.outfit.OutfitDTO;
import com.tibiainfo.model.dto.outfit.OutfitQuestDTO;
import com.tibiainfo.model.dto.query.OutfitQueryDTO;
import com.tibiainfo.model.enumeration.OutfitSex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OutfitServiceTests {

    private final Long EXISTING_OUTFIT = 80934L;
    private final Long NON_EXISTING_OUTFIT = -1L;
    private final Long OUTFIT_WITH_QUESTS = 8575L;

    private final OutfitSex OUTFIT_SEX = OutfitSex.MALE;
    private final Integer ADDON_ZERO = 0;

    private final String OUTFIT_NAME = "Retro Knight";

    private final OutfitQueryDTO.OutfitQueryDTOBuilder<?, ?> QUERY_DTO_BUILDER;
    @Autowired
    private OutfitService outfitService;

    {
        QUERY_DTO_BUILDER = OutfitQueryDTO.builder()
                .page(0)
                .size(10);
    }

    @Test
    public void testGetOutfits() {
        PageSupportDTO<OutfitDTO> outfits = outfitService.getOutfits(QUERY_DTO_BUILDER.build());

        assertNotNull(outfits);
        assertNotNull(outfits.getContent());
        assertNotNull(outfits.getNumberOfElements());
        assertNotNull(outfits.getTotalElements());
        assertNotNull(outfits.getTotalPages());

        assertFalse(outfits.isEmpty());
        assertFalse(outfits.getContent().isEmpty());
        assertFalse(outfits.isLast());
    }

    @Test
    public void testGetOutfitsOfName() {
        PageSupportDTO<OutfitDTO> outfits = outfitService.getOutfits(
                QUERY_DTO_BUILDER.name(Optional.of(OUTFIT_NAME)).extended(true).build()
        );

        assertNotNull(outfits);
        assertNotNull(outfits.getContent());
        assertFalse(outfits.isEmpty());
        assertTrue(
                outfits.getContent()
                        .stream()
                        .allMatch(outfit -> outfit.getName().equalsIgnoreCase(OUTFIT_NAME))
        );
    }

    @Test
    public void testGetOutfitById() throws NotFoundException {
        OutfitDTO fetchedOutfit = outfitService.getOutfitById(EXISTING_OUTFIT);

        assertNotNull(fetchedOutfit);
        assertEquals(EXISTING_OUTFIT, fetchedOutfit.getId());
    }

    @Test(expected = NotFoundException.class)
    public void testGetOutfitThatDoesNotExist() throws NotFoundException {
        outfitService.getOutfitById(NON_EXISTING_OUTFIT);
    }

    @Test
    public void testGetOutfitImage() throws NotFoundException {
        byte[] image = outfitService.getImage(EXISTING_OUTFIT, OUTFIT_SEX, ADDON_ZERO);

        assertNotNull(image);
    }

    @Test(expected = NotFoundException.class)
    public void testGetOutfitImageForAnOutfitThatDoesNotExist() throws NotFoundException {
        outfitService.getImage(NON_EXISTING_OUTFIT, OUTFIT_SEX, ADDON_ZERO);
    }

    @Test
    public void testGetOutfitsWithNonExtendedJson() {
        PageSupportDTO<OutfitDTO> outfits = outfitService.getOutfits(
                QUERY_DTO_BUILDER.build()
        );

        assertNotNull(outfits);
        assertNotNull(outfits.getContent());
        assertFalse(outfits.isEmpty());
        assertTrue(
                outfits.getContent()
                        .stream()
                        .map(OutfitDTO::getAchievement)
                        .allMatch(Objects::isNull)
        );
    }

    @Test
    public void testGetOutfitsWithExtendedJson() {
        PageSupportDTO<OutfitDTO> outfits = outfitService.getOutfits(
                QUERY_DTO_BUILDER.extended(true).build()
        );

        assertNotNull(outfits);
        assertNotNull(outfits.getContent());
        assertFalse(outfits.isEmpty());
        assertTrue(
                outfits.getContent()
                        .stream()
                        .map(OutfitDTO::getAchievement)
                        .anyMatch(Objects::nonNull)
        );
    }

    @Test
    public void testGetOutfitQuests() throws NotFoundException {
        List<OutfitQuestDTO> fetchedQuests = outfitService.getQuests(OUTFIT_WITH_QUESTS);

        assertNotNull(fetchedQuests);
        assertFalse(fetchedQuests.isEmpty());
    }

}
