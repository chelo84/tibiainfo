package com.tibiainfo.model.entity.outfit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Data
@Entity
@IdClass(value = OutfitImage.OutfitImageId.class)
public class OutfitImage {

    @Id
    Long outfitId;

    @Id
    String sex;

    @Id
    Integer addon;

    String image;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OutfitImageId implements Serializable {

        Long outfitId;

        String sex;

        Integer addon;

    }

}
