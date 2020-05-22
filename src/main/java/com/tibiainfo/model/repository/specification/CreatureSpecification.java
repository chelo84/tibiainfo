package com.tibiainfo.model.repository.specification;

import com.tibiainfo.model.dto.CreatureQueryDTO;
import com.tibiainfo.model.entity.creature.Creature;
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
public class CreatureSpecification implements Specification<Creature> {

    CreatureQueryDTO creatureQueryDto;

    @Override
    public Predicate toPredicate(Root<Creature> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        Predicate predicate = builder.and();

        if (creatureQueryDto.getName().isPresent()) {
            predicate = getNamePredicate(root, builder, predicate);
        }
        return predicate;
    }

    public Predicate getNamePredicate(Root<Creature> root, CriteriaBuilder builder, Predicate predicate) {
        Predicate creatureEquals = builder.equal(
                builder.upper(root.get("name")),
                creatureQueryDto.getName().get().toUpperCase());
        predicate = builder.and(predicate, creatureEquals);
        return predicate;
    }
}
