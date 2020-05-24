package com.tibiainfo.model.dto.map;

import com.tibiainfo.model.entity.map.Map;
import lombok.Data;

@Data
public class MapDTO {

    Long z;

    public MapDTO(Map map) {
        this.z = map.getZ();
    }

    public MapDTO(Map map, boolean extended) {
    }
}
