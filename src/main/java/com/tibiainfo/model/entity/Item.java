package com.tibiainfo.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
}
