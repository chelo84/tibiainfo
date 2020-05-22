package com.tibiainfo.model.entity.creature;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@Data
@IdClass(CreatureSoundId.class)
public class CreatureSound {

    @Id
    Long creatureId;

    @Id
    String content;

}
