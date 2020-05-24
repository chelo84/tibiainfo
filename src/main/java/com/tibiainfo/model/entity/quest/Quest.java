package com.tibiainfo.model.entity.quest;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Quest {

    @Id
    @Column(name = "article_id")
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "questId")
    List<QuestDanger> dangers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "questId")
    List<QuestReward> rewards = new ArrayList<>();

}
