package com.tibiainfo.model.dto.charm;

import com.tibiainfo.model.entity.charm.Charm;
import lombok.Data;

@Data
public class CharmDTO {

    Long id;

    String title;

    String name;

    String type;

    String effect;

    Integer cost;

    String version;

    Integer timestamp;

    public CharmDTO(Charm charm) {
        this(charm, true);
    }

    public CharmDTO(Charm charm, boolean extended) {
        this.id = charm.getId();
        this.title = charm.getTitle();
        this.cost = charm.getCost();

        if (extended) {
            this.name = charm.getName();
            this.type = charm.getType();
            this.effect = charm.getEffect();
            this.version = charm.getVersion();
            this.timestamp = charm.getTimestamp();
        }
    }

}
