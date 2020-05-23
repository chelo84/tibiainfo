package com.tibiainfo.model.entity.item.key;

import com.tibiainfo.model.entity.item.Item;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ItemKey {

    @Id
    @Column(name = "article_id")
    Long id;

    @Column(name = "item_id", updatable = false, insertable = false)
    Long itemId;

    @JoinColumn(name = "item_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Item item;

    String title;

    Integer number;

    String name;

    String material;

    String location;

    String origin;

    String notes;

    String version;

    Integer timestamp;

}
