package com.tibiainfo.model.entity.mount;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Mount {

    @Id
    @Column(name = "article_id")
    Long id;

    String title;

    String name;

    Integer speed;

    String tamingMethod;

    @Column(name = "buyable")
    Boolean buyAble;

    Integer price;

    String achievement;

    Integer lightColor;

    Integer lightRadius;

    String version;

    Integer timestamp;

}
