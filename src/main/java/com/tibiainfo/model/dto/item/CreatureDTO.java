package com.tibiainfo.model.dto.item;

import com.tibiainfo.model.entity.Creature;
import lombok.Data;

@Data
public class CreatureDTO {

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

    Integer modifierHpDrain;

    String abilities;

    String walksThrough;

    String walksAround;

    String version;

    Integer timestamp;

    public CreatureDTO(Creature creature) {
        this.id = creature.getId();
        this.title = creature.getTitle();
        this.name = creature.getName();
        this.plural = creature.getPlural();
        this.article = creature.getArticle();
        this.hitpoints = creature.getHitpoints();
        this.experience = creature.getExperience();
        this.armor = creature.getArmor();
        this.speed = creature.getSpeed();
        this.creatureClass = creature.getCreatureClass();
        this.type = creature.getType();
        this.typeSecondary = creature.getTypeSecondary();
        this.bestiaryClass = creature.getBestiaryClass();
        this.bestiaryLevel = creature.getBestiaryLevel();
        this.bestiaryOccurrence = creature.getBestiaryOccurrence();
        this.maxDamage = creature.getMaxDamage();
        this.summonCost = creature.getSummonCost();
        this.illusionable = creature.getIllusionable();
        this.pushable = creature.getPushable();
        this.pushObjects = creature.getPushObjects();
        this.paralysable = creature.getParalysable();
        this.seesInvisible = creature.getSeesInvisible();
        this.boss = creature.getBoss();
        this.modifierPhysical = creature.getModifierPhysical();
        this.modifierEarth = creature.getModifierEarth();
        this.modifierFire = creature.getModifierFire();
        this.modifierIce = creature.getModifierIce();
        this.modifierEnergy = creature.getModifierEnergy();
        this.modifierHoly = creature.getModifierHoly();
        this.modifierDrown = creature.getModifierDrown();
        this.modifierHpDrain = creature.getModifierHpDrain();
        this.abilities = creature.getAbilities();
        this.walksThrough = creature.getWalksThrough();
        this.walksAround = creature.getWalksAround();
        this.version = creature.getVersion();
        this.timestamp = creature.getTimestamp();
    }
}
