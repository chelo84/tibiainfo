package com.tibiainfo.model.dto.item;

import com.tibiainfo.model.entity.item.ItemAttribute;
import lombok.Data;

@Data
public class ItemAttributeDTO {

    String name;

    String value;

    public ItemAttributeDTO(ItemAttribute itemAttribute) {
        this.name = itemAttribute.getName();
        this.value = itemAttribute.getValue();
    }

}
