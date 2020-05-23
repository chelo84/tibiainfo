package com.tibiainfo.model.entity.imbuement;

import com.tibiainfo.model.entity.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@IdClass(ImbuementMaterialId.class)
public class ImbuementMaterial {

    @Id
    Long imbuementId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    Item item;

    Integer amount;


}
