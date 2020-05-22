package com.tibiainfo.model.entity.creature;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class CreatureSound {

    @Id
    @Column(name = "creature_id")
    Long id;

    String content;
}
