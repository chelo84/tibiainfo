package com.tibiainfo.model.entity.creature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@Data
@IdClass(CreatureSound.CreatureSoundId.class)
public class CreatureSound {

    @Id
    Long creatureId;

    @Id
    String content;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreatureSoundId implements Serializable {

        Long creatureId;

        String content;

    }

}
