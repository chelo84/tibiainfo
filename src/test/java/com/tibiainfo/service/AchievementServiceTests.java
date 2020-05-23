package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.achievement.AchievementDTO;
import com.tibiainfo.model.dto.query.AchievementQueryDTO;
import com.tibiainfo.model.dto.query.AchievementQueryDTO.AchievementQueryDTOBuilder;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AchievementServiceTests {

    private final Long EXISTING_ACHIEVIEMENT = 16606L;
    private final Long NON_EXISTING_ACHIEVEMENT = -1L;

    private final String ACHIEVEMENT_NAME = "Scrapper";

    private final AchievementQueryDTOBuilder<?, ?> ACHIEVEMENT_QUERY_DTO_BUILDER;
    @Autowired
    private AchievementService achievementService;

    {
        ACHIEVEMENT_QUERY_DTO_BUILDER = AchievementQueryDTO.builder()
                .page(0)
                .size(10);
    }

    @Test
    public void testGetAchievement() {
        PageSupportDTO<AchievementDTO> achievements = achievementService.getAchievements(ACHIEVEMENT_QUERY_DTO_BUILDER.build());

        assertNotNull(achievements);
        assertNotNull(achievements.getContent());
        assertNotNull(achievements.getNumberOfElements());
        assertNotNull(achievements.getTotalElements());
        assertNotNull(achievements.getTotalPages());

        assertFalse(achievements.isEmpty());
        assertFalse(achievements.getContent().isEmpty());
        assertFalse(achievements.isLast());

    }

    @Test
    public void testGetAchievementByName() {
        AchievementQueryDTO achievementQueryDTO = AchievementQueryDTO.builder()
                .name(Optional.of(ACHIEVEMENT_NAME))
                .page(0)
                .size(10)
                .build();

        PageSupportDTO<AchievementDTO> achievements = achievementService.getAchievements(
                ACHIEVEMENT_QUERY_DTO_BUILDER.name(Optional.of(ACHIEVEMENT_NAME)).build()
        );
    }

    @Test
    public void testGetAchievementById() throws NotFoundException {
        AchievementDTO fetchedAchievement = achievementService.getAchievementById(EXISTING_ACHIEVIEMENT);

        assertNotNull(fetchedAchievement);
        assertEquals(EXISTING_ACHIEVIEMENT, fetchedAchievement.getId());

    }

    @Test(expected = NotFoundException.class)
    public void testGetAchievementThatDoesExist() throws NotFoundException {
        achievementService.getAchievementById(NON_EXISTING_ACHIEVEMENT);
    }


}
