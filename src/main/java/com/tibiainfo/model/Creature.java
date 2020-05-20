package com.tibiainfo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Creature {

    @Id
    @Column(name = "article_id")
    Long id;

    String title;

    String name;

    String plural;

    String article;

    String hitpoints;

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

    Integer illusionable;

    Integer pushable;

    Integer pushObjects;

    Integer paralysable;

    Integer seesInvisible;

    Integer boss;

    Integer modifierPhysical;

    Integer modifierEarth;

    Integer modifierFire;

    Integer modifierIce;

    Integer modifierEnergy;

    Integer modifierHoly;

    Integer modifierDrown;

    Integer modiferHpDrain;

    String abilities;

    String walksThrough;

    String walksAround;

    String version;

    Integer timestamp;


}
