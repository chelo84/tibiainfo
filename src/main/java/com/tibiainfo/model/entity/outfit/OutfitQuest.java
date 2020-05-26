package com.tibiainfo.model.entity.outfit;

import com.tibiainfo.model.entity.quest.Quest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@IdClass(OutfitQuest.OutfitQuestId.class)
public class OutfitQuest {

    @Id
    Long outfitId;

    @Id
    @JoinColumn(name = "quest_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Quest quest;

    String type;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OutfitQuestId implements Serializable {

        Long outfitId;

        Long quest;

    }

}
