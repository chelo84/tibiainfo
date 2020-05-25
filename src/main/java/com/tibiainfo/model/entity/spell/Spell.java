package com.tibiainfo.model.entity.spell;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Spell {

    @Id
    @Column(name = "article_id")
    Long id;

    String title;

    String name;

    String words;

    String effect;

    String type;

    @Column(name = "class")
    String className;

    String element;

    Integer level;

    Integer mana;

    Integer soul;

    Integer premium;

    Integer price;

    Integer cooldown;

    Boolean knight;

    Boolean sorcerer;

    Boolean druid;

    Boolean paladin;

    Integer timestamp;
}
