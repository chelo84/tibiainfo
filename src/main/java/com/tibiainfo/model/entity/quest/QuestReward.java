package com.tibiainfo.model.entity.quest;

import com.tibiainfo.model.entity.item.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@IdClass(QuestReward.QuestRewardId.class)
public class QuestReward {

    @Id
    Long questId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    Item item;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuestRewardId implements Serializable {

        Long questId;

        Long item;

    }

}