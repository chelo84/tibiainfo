package com.tibiainfo.model.dto.creature;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tibiainfo.model.dto.item.ItemDTO;
import com.tibiainfo.model.entity.creature.CreatureDrop;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
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
