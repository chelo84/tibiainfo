package com.tibiainfo.exception;

import com.tibiainfo.model.entity.achievement.Achievement;
import com.tibiainfo.model.entity.charm.Charm;
import com.tibiainfo.model.entity.creature.Creature;
import com.tibiainfo.model.entity.house.House;
import com.tibiainfo.model.entity.imbuement.Imbuement;
import com.tibiainfo.model.entity.item.Item;
import com.tibiainfo.model.entity.item.key.ItemKey;
import com.tibiainfo.model.entity.outfit.Outfit;
import com.tibiainfo.model.entity.outfit.OutfitImage;
import com.tibiainfo.model.entity.quest.Quest;
import com.tibiainfo.model.entity.spell.Spell;

import java.util.Map;

import static java.util.Map.entry;

public class NotFoundException extends Exception {

    private static final Map<Class<?>, String> notFoundCommand = Map.ofEntries(
            entry(Item.class, "Item not found"),
            entry(Charm.class, "Charm not found"),
            entry(Outfit.class, "Outfit not found"),
            entry(OutfitImage.class, "Outfit's image not found"),
            entry(Achievement.class, "Achievement not found"),
            entry(Imbuement.class, "Imbuement not found"),
            entry(Spell.class, "Spell not found"),
            entry(Map.class, "Map not found"),
            entry(Creature.class, "Creature not found"),
            entry(House.class, "House not found"),
            entry(Quest.class, "Quest not found"),
            entry(ItemKey.class, "Key not found")
    );

    public NotFoundException(Class<?> clazz) {
        super(notFoundCommand.getOrDefault(clazz, "Not found"));
    }

}
