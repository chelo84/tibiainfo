package com.tibiainfo.model.entity.imbuement;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Imbuement {

    @Id
    @Column(name = "article_id")
    Long id;

    String title;

    String name;

    String tier;

    String type;

    String effect;

    String version;

    Integer timestamp;

}
