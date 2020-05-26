package com.tibiainfo.model.dto.mount;

import com.tibiainfo.model.entity.mount.Mount;
import lombok.Data;

@Data
public class MountDTO {

    Long id;

    String title;

    String name;

    Integer speed;

    String tamingMethod;

    Boolean buyAble;

    Integer price;

    String achievement;

    Integer lightColor;

    Integer lightRadius;

    String version;

    Integer timestamp;

    public MountDTO(Mount mount) {
        this(mount, true);
    }

    public MountDTO(Mount mount, boolean extended) {
        this.id = mount.getId();
        this.name = mount.getName();
        this.speed = mount.getSpeed();

        if (extended) {
            this.title = mount.getTitle();
            this.tamingMethod = mount.getTamingMethod();
            this.buyAble = mount.getBuyAble();
            this.price = mount.getPrice();
            this.achievement = mount.getAchievement();
            this.lightColor = mount.getLightColor();
            this.lightRadius = mount.getLightRadius();
            this.timestamp = mount.getTimestamp();
            this.version = mount.getVersion();
        }
    }
}
