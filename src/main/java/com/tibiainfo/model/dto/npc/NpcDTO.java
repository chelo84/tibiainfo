package com.tibiainfo.model.dto.npc;

import com.tibiainfo.model.entity.npc.Npc;
import lombok.Data;

@Data
public class NpcDTO {

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

    public NpcDTO(Npc npc){
        this(npc, true);
    }


    public NpcDTO(Npc npc, boolean extended){
        this.id = npc.getId();
        this.title = npc.getTitle();
        this.city = npc.getCity();

        if(extended) {
            this.name = npc.getName();
            this.race = npc.getRace();
            this.gender = npc.getGender();
            this.location = npc.getLocation();
            this.job = npc.getJob();
            this.version = npc.getVersion();
            this.x = npc.getX();
            this.y = npc.getY();
            this.z = npc.getZ();
        }
    }
}
