package com.tibiainfo.model.entity.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemAttributeId implements Serializable {

    Long itemId;

    String name;

    String value;

}
