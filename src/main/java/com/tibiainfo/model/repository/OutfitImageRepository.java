package com.tibiainfo.model.repository;

import com.tibiainfo.model.entity.outfit.OutfitImage;
import com.tibiainfo.model.entity.outfit.OutfitImage.OutfitImageId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OutfitImageRepository extends JpaRepository<OutfitImage, OutfitImageId> {

    Optional<OutfitImage> findFirstByOutfitIdAndSexAndAddon(Long outfitId,
                                                            String sex,
                                                            Integer addon);

}
