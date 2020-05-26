package com.tibiainfo.model.dto.rashid;

import com.tibiainfo.model.entity.rashid.Rashid;
import lombok.Data;

@Data
public class RashidDTO {

    Long day;

    String city;

    String location;

    Integer x;

    Integer y;

    Integer z;

    public RashidDTO(Rashid rashid) {
        this(rashid, true);
    }

    public RashidDTO(Rashid rashid, boolean extended) {
        this.day = rashid.getDay();
        this.city = rashid.getCity();
        this.location = rashid.getLocation();
        if (extended) {
            this.x = rashid.getX();
            this.y = rashid.getY();
            this.z = rashid.getZ();
        }
    }
}
