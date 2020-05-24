package com.tibiainfo.model.entity.imbuement;

import com.tibiainfo.model.entity.item.Item;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@IdClass(ImbuementMaterial.ImbuementMaterialId.class)
public class ImbuementMaterial {

    @Id
    Long imbuementId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    Item item;

    Integer amount;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImbuementMaterialId implements Serializable {

        Long imbuementId;

        Long item;
    }


}