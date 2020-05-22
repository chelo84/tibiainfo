package com.tibiainfo.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Achievement {

    @Id
    @Column(name = "article_id")
    Long id;

    String title;

    String name;

    Integer grade;

    Integer points;

    String description;

    String spoiler;

    Boolean secret;

    Boolean premium;

    String version;

    Integer timestamp;

}
