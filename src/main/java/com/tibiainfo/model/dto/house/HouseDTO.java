package com.tibiainfo.model.dto.house;

import com.tibiainfo.model.entity.house.House;
import lombok.Data;

@Data
public class HouseDTO {

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

    public HouseDTO(House house) {
        this(house, true);
    }

    public HouseDTO(House house, boolean extended) {
        this.id = house.getId();
        this.houseId = house.getHouseId();
        this.name = house.getName();
        this.title = house.getTitle();
        this.city = house.getCity();

        if (extended) {
            this.street = house.getStreet();
            this.location = house.getLocation();
            this.beds = house.getBeds();
            this.rent = house.getRent();
            this.size = house.getSize();
            this.rooms = house.getRooms();
            this.floors = house.getFloors();
            this.x = house.getX();
            this.y = house.getY();
            this.z = house.getZ();
            this.guildhall = house.getGuildhall();
            this.version = house.getVersion();
            this.timestamp = house.getTimestamp();
        }
    }

}
