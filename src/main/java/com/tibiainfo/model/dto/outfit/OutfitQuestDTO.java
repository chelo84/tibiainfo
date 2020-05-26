package com.tibiainfo.model.dto.outfit;

import com.tibiainfo.model.dto.quest.QuestDTO;
import com.tibiainfo.model.entity.outfit.OutfitQuest;
import lombok.Data;

@Data
public class OutfitQuestDTO {

    Long outfitId;

    QuestDTO quest;

    String type;

    public OutfitQuestDTO(OutfitQuest outfitQuest) {
        this.outfitId = outfitQuest.getOutfitId();
        this.quest = new QuestDTO(outfitQuest.getQuest());
        this.type = outfitQuest.getType();
    }

}
