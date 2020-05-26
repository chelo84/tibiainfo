package com.tibiainfo.model.entity.outfit;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Outfit {

    @Id
    @Column(name = "article_id")
    Long id;

    String title;

    String name;

    String type;

    Boolean premium;

    Boolean bought;

    Boolean tournament;

    Integer fullPrice;

    String achievement;

    String version;

    Integer timestamp;

}
