package com.tibiainfo.model.entity.item;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@Data
@IdClass(ItemAttributeId.class)
public class ItemAttribute {

    @Id
    Long itemId;

    @Id
    String name;

    @Id
    String value;

}
