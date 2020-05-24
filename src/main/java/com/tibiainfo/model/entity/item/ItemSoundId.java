package com.tibiainfo.model.entity.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemSoundId implements Serializable {

    Long itemId;

    String content;

}
