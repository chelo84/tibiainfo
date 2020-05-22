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
        this.id = charm.getId();
        this.title = charm.getTitle();
        this.name = charm.getName();
        this.type = charm.getType();
        this.effect = charm.getEffect();
        this.cost = charm.getCost();
        this.version = charm.getVersion();
        this.timestamp = charm.getTimestamp();
    }
}
