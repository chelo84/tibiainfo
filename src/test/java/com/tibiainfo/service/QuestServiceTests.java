package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.query.QuestQueryDTO;
import com.tibiainfo.model.dto.quest.QuestDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@RunWith(SpringRunner.class)
public class QuestServiceTests {

    private final Long EXISTING_QUEST = 2496L;
    private final Long NON_EXISTING_QUEST = -1L;

    private final String QUEST_NAME = "The desert dungeon quest";

    private final QuestQueryDTO.QuestQueryDTOBuilder<?, ?> QUERY_DTO_BUILDER;
    @Autowired
    private QuestService questService;

    {
        QUERY_DTO_BUILDER = QuestQueryDTO.builder()
                .page(0)
                .size(10);
    }

    @Test
    public void testGetQuests() {
        PageSupportDTO<QuestDTO> quests = questService.getQuests(QUERY_DTO_BUILDER.build());

        assertNotNull(quests);
        assertNotNull(quests.getContent());
        assertNotNull(quests.getNumberOfElements());
        assertNotNull(quests.getTotalElements());
        assertNotNull(quests.getTotalPages());

        assertFalse(quests.isEmpty());
        assertFalse(quests.getContent().isEmpty());
        assertFalse(quests.isLast());
    }

    @Test
    public void testGetQuestsByName() {
        PageSupportDTO<QuestDTO> quests = questService.getQuests(
                QUERY_DTO_BUILDER.name(Optional.of(QUEST_NAME)).extended(true).build()
        );

        assertNotNull(quests);
        assertNotNull(quests.getContent());
        assertFalse(quests.isEmpty());
        assertTrue(
                quests.getContent()
                        .stream()
                        .allMatch(quest -> quest.getName().equalsIgnoreCase(QUEST_NAME))
        );
    }

    @Test
    public void testGetQuestById() throws NotFoundException {
        QuestDTO fetchedQuest = questService.getQuestById(EXISTING_QUEST);

        assertNotNull(fetchedQuest);
        assertEquals(EXISTING_QUEST, fetchedQuest.getId());
    }

    @Test(expected = NotFoundException.class)
    public void testGetQuestThatDoesNotExist() throws NotFoundException {
        questService.getQuestById(NON_EXISTING_QUEST);
    }

    @Test
    public void testGetQuestsWithNonExtendedJson() {
        PageSupportDTO<QuestDTO> quests = questService.getQuests(
                QUERY_DTO_BUILDER.build()
        );

        assertNotNull(quests);
        assertNotNull(quests.getContent());
        assertFalse(quests.isEmpty());
        assertTrue(
                quests.getContent()
                        .stream()
                        .map(QuestDTO::getQuestLog)
                        .allMatch(Objects::isNull)
        );
    }

    @Test
    public void testGetQuestsWithExtendedJson() {
        PageSupportDTO<QuestDTO> quests = questService.getQuests(
                QUERY_DTO_BUILDER.extended(true).build()
        );

        assertNotNull(quests);
        assertNotNull(quests.getContent());
        assertFalse(quests.isEmpty());
        assertTrue(
                quests.getContent()
                        .stream()
                        .map(QuestDTO::getQuestLog)
                        .anyMatch(Objects::nonNull)
        );
    }

}
