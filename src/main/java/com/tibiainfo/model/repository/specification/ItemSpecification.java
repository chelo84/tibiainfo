package com.tibiainfo.model.repository.specification;

import com.tibiainfo.model.Item;
import com.tibiainfo.model.dto.ItemQueryDTO;
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
public class ItemSpecification implements Specification<Item> {

    ItemQueryDTO itemQueryDto;

    @Override
    public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Predicate predicate = builder.and();

        if (itemQueryDto.getType().isPresent()) {
            predicate = getTypePredicate(root, builder, predicate);
        }
        if (itemQueryDto.getName().isPresent()) {
            predicate = getNamePredicate(root, builder, predicate);
        }

        return predicate;
    }

    private Predicate getTypePredicate(Root<Item> root, CriteriaBuilder builder, Predicate predicate) {
        Predicate typeEquals = builder.equal(
                builder.upper(root.get("type")),
                itemQueryDto.getType().get().toUpperCase()
        );
        predicate = builder.and(predicate, typeEquals);
        return predicate;
    }

    ;

    private Predicate getNamePredicate(Root<Item> root, CriteriaBuilder builder, Predicate predicate) {
        Predicate titleEquals = builder.equal(
                builder.upper(root.get("name")),
                itemQueryDto.getName().get().toUpperCase()
        );
        predicate = builder.and(predicate, titleEquals);
        return predicate;

    }
}