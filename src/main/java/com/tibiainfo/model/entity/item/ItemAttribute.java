package com.tibiainfo.model.entity.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@Data
@IdClass(ItemAttribute.ItemAttributeId.class)
public class ItemAttribute {

    @Id
    Long itemId;

    @Id
    String name;

    @Id
    String value;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ItemAttributeId implements Serializable {

        Long itemId;

        String name;

        String value;

    }

}
