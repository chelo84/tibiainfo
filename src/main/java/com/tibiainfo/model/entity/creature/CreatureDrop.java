package com.tibiainfo.model.entity.creature;

import com.tibiainfo.model.entity.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@IdClass(CreatureDropId.class)
public class CreatureDrop {

    @Id
    Long creatureId;

    @Id
    @ManyToOne
    @JoinColumn(name = "item_id")
    Item item;

    Double chance;

    Integer min;

    Integer max;

}