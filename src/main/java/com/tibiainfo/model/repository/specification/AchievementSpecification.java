package com.tibiainfo.model.repository.specification;

import com.tibiainfo.model.dto.AchievementQueryDTO;
import com.tibiainfo.model.entity.Achievement;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AchievementSpecification implements Specification<Achievement> {

    AchievementQueryDTO achievementQueryDTO;

    @Override
    public Predicate toPredicate(Root<Achievement> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        Predicate predicate = builder.and();

        if (achievementQueryDTO.getName().isPresent()) {
            predicate = getNamePredicate(root, builder, predicate);
        }
        return predicate;
    }

    public Predicate getNamePredicate(Root<Achievement> root, CriteriaBuilder builder, Predicate predicate) {
        Predicate creatureEquals = builder.equal(
                builder.upper(root.get("name")),
                achievementQueryDTO.getName().get().toUpperCase());
        predicate = builder.and(predicate, creatureEquals);
        return predicate;
    }

}
