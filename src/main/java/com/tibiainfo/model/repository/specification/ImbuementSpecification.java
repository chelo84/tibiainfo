package com.tibiainfo.model.repository.specification;

import com.tibiainfo.model.dto.query.ImbuementQueryDTO;
import com.tibiainfo.model.entity.imbuement.Imbuement;
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
public class ImbuementSpecification implements Specification<Imbuement> {

    ImbuementQueryDTO imbuementQueryDTO;

    @Override
    public Predicate toPredicate(Root<Imbuement> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        Predicate predicate = builder.and();
        if (imbuementQueryDTO.getName().isPresent()) {
            predicate = getNamePredicate(root, builder, predicate);
        }
        return predicate;
    }

    private Predicate getNamePredicate(Root<Imbuement> root, CriteriaBuilder builder, Predicate predicate) {
        Predicate imbuementEquals = builder.equal(
                builder.upper(root.get("name")),
                imbuementQueryDTO.getName().get().toUpperCase());
        predicate = builder.and(predicate, imbuementEquals);
        return predicate;
    }
}

