package com.tibiainfo.model.entity.rashid;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "rashid_position")
@Entity
@Data
public class Rashid {

    @Id
    Long day;

    String city;

    String location;

    Integer x;

    Integer y;

    Integer z;
}
