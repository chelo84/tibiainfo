package com.tibiainfo.model.entity.quest;

import com.tibiainfo.model.entity.creature.Creature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@IdClass(QuestDanger.QuestDangerId.class)
public class QuestDanger {

    @Id
    Long questId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creature_id")
    Creature creature;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuestDangerId implements Serializable {

        Long questId;

        Long creature;

    }

}