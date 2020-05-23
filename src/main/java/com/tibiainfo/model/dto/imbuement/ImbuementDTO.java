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

    List<ImbuementMaterialDTO> imbuement;

    public ImbuementDTO(Imbuement imbuement) {
        this.id = imbuement.getId();
        this.title = imbuement.getTitle();
        this.name = imbuement.getName();
        this.tier = imbuement.getTier();
        this.type = imbuement.getType();
        this.effect = imbuement.getEffect();
        this.version = imbuement.getVersion();
        this.timestamp = imbuement.getTimestamp();
        this.imbuement = imbuement.getImbuements()
                .stream()
                .map(ImbuementMaterialDTO::new)
                .collect(Collectors.toList());
    }
}
