package com.tibiainfo.model.entity.house;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class House {

    @Id
    @Column(name = "article_id")
    Long id;

    Long houseId;

    String title;

    String name;

    String city;

    String street;

    String location;

    Integer beds;

    Integer rent;

    Integer size;

    Integer rooms;

    Integer floors;

    Integer x;

    Integer y;

    Integer z;

    Integer guildhall;

    String version;

    Integer timestamp;

}
