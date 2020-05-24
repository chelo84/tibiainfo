package com.tibiainfo.model.dto.quest;

import com.tibiainfo.model.dto.creature.CreatureDTO;
import com.tibiainfo.model.dto.item.ItemDTO;
import com.tibiainfo.model.entity.quest.Quest;
import com.tibiainfo.model.entity.quest.QuestDanger;
import com.tibiainfo.model.entity.quest.QuestReward;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class QuestDTO {

    Long id;

    String title;

    String name;

    String location;

    Boolean rookgaard;

    String type;

    Boolean questLog;

    String legend;

    Integer levelRequired;

    Integer levelRecommended;

    String activeTime;

    String estimatedTime;

    Boolean premium;

    String version;

    Integer timestamp;

    List<CreatureDTO> dangers = new ArrayList<>();

    List<ItemDTO> rewards = new ArrayList<>();

    public QuestDTO(Quest quest) {
        this(quest, true);
    }

    public QuestDTO(Quest quest, boolean extended) {
        this.id = quest.getId();
        this.title = quest.getTitle();

        if (extended) {
            this.name = quest.getName();
            this.location = quest.getLocation();
            this.rookgaard = quest.getRookgaard();
            this.type = quest.getType();
            this.questLog = quest.getQuestLog();
            this.legend = quest.getLegend();
            this.levelRequired = quest.getLevelRequired();
            this.levelRecommended = quest.getLevelRecommended();
            this.activeTime = quest.getActiveTime();
            this.estimatedTime = quest.getEstimatedTime();
            this.premium = quest.getPremium();
            this.version = quest.getVersion();
            this.timestamp = quest.getTimestamp();
            this.dangers = quest.getDangers()
                    .stream()
                    .map(QuestDanger::getCreature)
                    .map(creature -> new CreatureDTO(creature, false))
                    .collect(Collectors.toList());
            this.rewards = quest.getRewards()
                    .stream()
                    .map(QuestReward::getItem)
                    .map(item -> new ItemDTO(item, false))
                    .collect(Collectors.toList());
        }
    }
}
