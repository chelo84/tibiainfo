package com.tibiainfo.model.dto.item.key;

import com.tibiainfo.model.entity.item.key.ItemKey;
import lombok.Data;

@Data
public class ItemKeyDTO {

    Long id;

    Long itemId;

    String title;

    Integer number;

    String name;

    String material;

    String location;

    String origin;

    String notes;

    String version;

    Integer timestamp;

    public ItemKeyDTO(ItemKey itemKey) {
        this(itemKey, true);
    }

    public ItemKeyDTO(ItemKey itemKey, boolean extended) {
        this.id = itemKey.getId();
        this.itemId = itemKey.getItemId();
        this.title = itemKey.getTitle();
        this.number = itemKey.getNumber();

        if (extended) {
            this.name = itemKey.getName();
            this.material = itemKey.getMaterial();
            this.location = itemKey.getLocation();
            this.origin = itemKey.getOrigin();
            this.notes = itemKey.getNotes();
            this.version = itemKey.getVersion();
            this.timestamp = itemKey.getTimestamp();
        }
    }

}
