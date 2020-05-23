package com.tibiainfo.model.dto.creature;

import com.tibiainfo.model.dto.item.ItemDTO;
import com.tibiainfo.model.entity.creature.CreatureDrop;
import lombok.Data;

@Data
public class CreatureDropDTO {

    ItemDTO item;

    Double chance;

    Integer min;

    Integer max;

    public CreatureDropDTO(CreatureDrop creatureDrop) {
        this.item = new ItemDTO(creatureDrop.getItem());
        this.chance = creatureDrop.getChance();
        this.min = creatureDrop.getMin();
        this.max = creatureDrop.getMax();
    }

}
