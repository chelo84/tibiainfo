package com.tibiainfo.model.repository;

import com.tibiainfo.model.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long>, JpaSpecificationExecutor<Achievement> {
}
