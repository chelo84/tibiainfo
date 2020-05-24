package com.tibiainfo.model.repository.specification;

import com.tibiainfo.model.dto.query.MapQueryDTO;
import com.tibiainfo.model.entity.map.Map;
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
public class MapSpecification implements Specification<Map> {

    MapQueryDTO mapQueryDTO;


    @Override
    public Predicate toPredicate(Root<Map> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        Predicate predicate = builder.and();
        if (mapQueryDTO.getZ().isPresent()) {
            predicate = getZPredicate(root, builder, predicate);
        }
        return predicate;
    }

    private Predicate getZPredicate(Root<Map> root, CriteriaBuilder builder, Predicate predicate) {
        Predicate zEquals = builder.equal(
                root.get("z"),
                mapQueryDTO.getZ().get());
        predicate = builder.and(predicate, zEquals);
        return predicate;

    }
}
