package com.tibiainfo.model.dto.spell;

import com.tibiainfo.model.entity.spell.Spell;
import lombok.Data;

@Data
public class SpellDTO {

    Long id;

    String title;

    String name;

    String words;

    String effect;

    String type;

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

    public SpellDTO(Spell spell) {
        this(spell, true);
    }

    public SpellDTO(Spell spell, boolean extended) {
        this.id = spell.getId();
        this.title = spell.getTitle();
        this.words = spell.getWords();
        this.mana = spell.getMana();
        this.level = spell.getLevel();
        this.price = spell.getPrice();
        this.level = spell.getLevel();
        this.cooldown = spell.getCooldown();

        if (extended) {
            this.name = spell.getName();
            this.effect = spell.getEffect();
            this.type = spell.getType();
            this.className = spell.getClassName();
            this.element = spell.getElement();
            this.soul = spell.getSoul();
            this.premium = spell.getPremium();
            this.knight = spell.getKnight();
            this.sorcerer = spell.getSorcerer();
            this.druid = spell.getDruid();
            this.paladin = spell.getPaladin();
            this.timestamp = spell.getTimestamp();
        }
    }
}
