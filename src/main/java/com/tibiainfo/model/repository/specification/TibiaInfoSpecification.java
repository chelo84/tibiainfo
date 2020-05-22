package com.tibiainfo.model.repository.specification;

import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

@SuperBuilder
public abstract class TibiaInfoSpecification<T> implements Specification<T> {

    Root<T> root;
    CriteriaQuery<?> query;
    CriteriaBuilder builder;
    Predicate predicate;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        this.root = root;
        this.query = criteriaQuery;
        this.builder = criteriaBuilder;
        this.predicate = builder.and();

        instructions();

        return Optional.ofNullable(predicate)
                .orElseGet(builder::and);
    }

    public void instructions() {
    }

    TibiaInfoSpecification<T> equal(String field, Optional<String> optional) {
        if (optional.isPresent()) {
            Predicate titleEquals = builder.equal(
                    builder.upper(root.get(field)),
                    optional.get().toUpperCase()
            );
            predicate = builder.and(predicate, titleEquals);
        }

        return this;
    }

    TibiaInfoSpecification<T> like(String field, Optional<String> optional) {
        if (optional.isPresent()) {
            Predicate titleEquals = builder.like(
                    builder.upper(root.get(field)),
                    optional.get().toUpperCase()
            );
            predicate = builder.and(predicate, titleEquals);
        }

        return this;
    }

}
