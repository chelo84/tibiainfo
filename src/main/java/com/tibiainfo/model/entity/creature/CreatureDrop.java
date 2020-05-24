package com.tibiainfo.model.entity.creature;

import com.tibiainfo.model.entity.item.Item;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@IdClass(CreatureDrop.CreatureDropId.class)
public class CreatureDrop {

    @Id
    Long creatureId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    Item item;

    Double chance;

    Integer min;

    Integer max;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreatureDropId implements Serializable {

        Long creatureId;

        Long item;

    }

}