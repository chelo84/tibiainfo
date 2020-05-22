package com.tibiainfo.model.repository.specification;

import com.tibiainfo.model.dto.query.CharmQueryDTO;
import com.tibiainfo.model.entity.Charm;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CharmSpecification implements Specification<Charm> {

    CharmQueryDTO charmQueryDTO;

    @Override
    public Predicate toPredicate(Root<Charm> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        Predicate predicate = builder.and();
        if (charmQueryDTO.getName().isPresent()) {
            predicate = getNamePredicate(root, builder, predicate);
        }
        return predicate;
    }

    private Predicate getNamePredicate(Root<Charm> root, CriteriaBuilder builder, Predicate predicate) {
        Predicate charmEquals = builder.equal(
                builder.upper(root.get("name")),
                charmQueryDTO.getName().get().toUpperCase());
        predicate = builder.and(predicate, charmEquals);
        return predicate;

    }
}
