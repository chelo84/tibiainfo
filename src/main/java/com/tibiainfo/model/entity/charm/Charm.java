package com.tibiainfo.model.entity.charm;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Charm {

    @Id
    @Column(name = "article_id")
    Long id;

    String title;

    String name;

    String type;

    String effect;

    Integer cost;

    String version;

    Integer timestamp;

}
