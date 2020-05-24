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
@IdClass(ItemSound.ItemSoundId.class)
public class ItemSound {

    @Id
    Long itemId;

    @Id
    String content;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ItemSoundId implements Serializable {

        Long itemId;

        String content;

    }

}