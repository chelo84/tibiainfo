package com.tibiainfo.model.entity.item;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Item {

    @Id
    @Column(name = "article_id")
    Long id;

    String title;

    String name;

    String plural;

    String article;

    Boolean marketable;

    Boolean stackable;

    Boolean pickupable;

    Integer valueSell;

    Integer valueBuy;

    Double weight;

    String itemClass;

    String type;

    String typeSecondary;

    String flavorText;

    Integer lightColor;

    Integer lightRadius;

    String version;

    Integer clientId;

    Integer timestamp;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemId")
    List<ItemAttribute> attributes = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemId")
    List<ItemSound> sounds = new ArrayList<>();

}
