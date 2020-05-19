package com.tibiainfo.model.repository.specification;

import com.tibiainfo.model.Item;
import com.tibiainfo.model.dto.ItemQueryDto;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemSpecification implements Specification<Item> {

    ItemQueryDto itemQueryDto;

    @Override
    public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Predicate predicate = builder.and();

        if (isNotBlank(itemQueryDto.getType())) {
            Predicate typeEquals = builder.equal(
                    builder.upper(root.get("type")),
                    itemQueryDto.getType().toUpperCase()
            );
            predicate = builder.and(predicate, typeEquals);
        }

        return predicate;
    }
}