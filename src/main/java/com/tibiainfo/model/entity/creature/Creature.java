package com.tibiainfo.model.entity.creature;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class Creature {

    @Id
    @Column(name = "article_id")
    Long id;

    String title;

    String name;

    String plural;

    String article;

    Integer hitpoints;

    Integer experience;

    Integer armor;

    Integer speed;

    String creatureClass;

    String type;

    String typeSecondary;

    String bestiaryClass;

    String bestiaryLevel;

    String bestiaryOccurrence;

    Integer maxDamage;

    Integer summonCost;

    Boolean illusionable;

    Boolean pushable;

    Boolean pushObjects;

    Boolean paralysable;

    Boolean seesInvisible;

    Boolean boss;

    Integer modifierPhysical;

    Integer modifierEarth;

    Integer modifierFire;

    Integer modifierIce;

    Integer modifierEnergy;

    Integer modifierHoly;

    Integer modifierDrown;

    @Column(name = "modifier_hpdrain")
    Integer modifierHpDrain;

    String abilities;

    String walksThrough;

    String walksAround;

    String version;

    Integer timestamp;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creatureId")
    List<CreatureSound> sounds;

}
