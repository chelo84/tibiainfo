package com.tibiainfo.model.repository;

import com.tibiainfo.model.entity.outfit.OutfitQuest;
import com.tibiainfo.model.entity.outfit.OutfitQuest.OutfitQuestId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutfitQuestRepository extends JpaRepository<OutfitQuest, OutfitQuestId> {

    List<OutfitQuest> findAllByOutfitId(Long id);

}
