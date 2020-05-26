package com.tibiainfo.model.dto.outfit;

import com.tibiainfo.model.entity.outfit.Outfit;
import lombok.Data;

@Data
public class OutfitDTO {

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

    public OutfitDTO(Outfit outfit) {
        this(outfit, true);
    }

    public OutfitDTO(Outfit outfit, boolean extended) {
        this.id = outfit.getId();
        this.title = outfit.getTitle();

        if (extended) {
            this.name = outfit.getName();
            this.type = outfit.getType();
            this.premium = outfit.getPremium();
            this.bought = outfit.getBought();
            this.tournament = outfit.getTournament();
            this.fullPrice = outfit.getFullPrice();
            this.achievement = outfit.getAchievement();
            this.version = outfit.getVersion();
            this.timestamp = outfit.getTimestamp();
        }
    }
}
