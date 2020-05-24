package com.tibiainfo.model.entity.item;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@Data
@IdClass(ItemSoundId.class)
public class ItemSound {

    @Id
    Long itemId;

    @Id
    String content;

}
