package com.tibiainfo.model.entity.creature;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CreatureDropId implements Serializable {

    Long creatureId;

    Long item;

}
