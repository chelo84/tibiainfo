package com.tibiainfo.model.entity.npc;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Npc {

    @Id
    @Column(name = "article_id")
    Long id;

    String title;

    String name;

    String race;

    String gender;

    String city;

    String location;

    String job;

    String version;

    String x;

    String y;

    String z;



}
