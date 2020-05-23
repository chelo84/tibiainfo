package com.tibiainfo.model.dto.imbuement;

import com.tibiainfo.model.dto.item.ItemDTO;
import com.tibiainfo.model.entity.imbuement.ImbuementMaterial;
import lombok.Data;

@Data
public class ImbuementMaterialDTO {

    ItemDTO item;

    Integer amount;

    public ImbuementMaterialDTO(ImbuementMaterial imbuementMaterial) {

        this.item = new ItemDTO(imbuementMaterial.getItem());
        this.amount = imbuementMaterial.getAmount();


    }
}
