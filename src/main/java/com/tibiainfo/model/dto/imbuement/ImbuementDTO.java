package com.tibiainfo.model.dto.imbuement;

import com.tibiainfo.model.entity.imbuement.Imbuement;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ImbuementDTO {

    Long id;

    String title;

    String name;

    String tier;

    String type;

    String effect;

    String version;

    Integer timestamp;

    List<ImbuementMaterialDTO> materials;

    public ImbuementDTO(Imbuement materials) {
        this(materials, true);
    }

    public ImbuementDTO(Imbuement materials, boolean extended) {
        this.id = materials.getId();
        this.title = materials.getTitle();

        if (extended) {
            this.name = materials.getName();
            this.tier = materials.getTier();
            this.type = materials.getType();
            this.effect = materials.getEffect();
            this.version = materials.getVersion();
            this.timestamp = materials.getTimestamp();
            this.materials = materials.getImbuements()
                    .stream()
                    .map(ImbuementMaterialDTO::new)
                    .collect(Collectors.toList());
        }
    }
}
