package com.tibiainfo.model.repository.specification;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Predicate.BooleanOperator;
import javax.persistence.criteria.Root;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.nonNull;

@SuperBuilder
@NoArgsConstructor
public abstract class TibiaInfoSpecification<T> implements Specification<T> {

    Root<T> root;
    CriteriaQuery<?> query;
    CriteriaBuilder builder;

    Predicate predicate;
    Predicate currentPredicate;

    BooleanOperator operator;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        this.root = root;
        this.query = criteriaQuery;
        this.builder = criteriaBuilder;
        this.predicate = builder.and();

        instructions();

        mergeCurrentPredicateToPredicate();

        return Optional.ofNullable(predicate)
                .orElseGet(builder::and);
    }

    private void mergeCurrentPredicateToPredicate() {
        if (nonNull(currentPredicate)) {
            if (currentPredicate.getOperator().equals(BooleanOperator.AND)
                    || predicate.getExpressions().isEmpty()) {
                predicate = builder.and(predicate, currentPredicate);
            } else {
                predicate = builder.or(predicate, currentPredicate);
            }
            currentPredicate = null;
        }
    }

    public void instructions() {
    }

    private void addInstruction(Predicate predicateToAdd) {
        if (nonNull(operator)) {
            currentPredicate = Optional.ofNullable(currentPredicate)
                    .map(newPredicate(predicateToAdd))
                    .orElseGet(newPredicateWithoutCurrentPredicate(predicateToAdd));

            operator = null;
        } else {
            currentPredicate = builder.and(
                    Optional.ofNullable(currentPredicate)
                            .orElse(predicate),
                    predicateToAdd
            );
        }
    }

    private Supplier<Predicate> newPredicateWithoutCurrentPredicate(Predicate predicateToAdd) {
        switch (operator) {
            case AND: {
                return () -> builder.and(predicateToAdd);
            }
            case OR: {
                return () -> builder.or(predicateToAdd);
            }
            default:
                throw new RuntimeException("Operator not found");
        }
    }

    private Function<Predicate, Predicate> newPredicate(Predicate predicateToAdd) {
        switch (operator) {
            case AND: {
                return (currentPredicate) -> builder.and(currentPredicate, predicateToAdd);
            }
            case OR: {
                return (currentPredicate) -> builder.or(currentPredicate, predicateToAdd);
            }
            default:
                throw new RuntimeException("Operator not found");
        }
    }

    TibiaInfoSpecification<T> equalIgnoreCase(String field, Optional<String> optional) {
        if (optional.isPresent()) {
            Predicate equalPredicate = builder.equal(
                    builder.upper(root.get(field)),
                    optional.get().toUpperCase()
            );

            addInstruction(equalPredicate);
        }

        return this;
    }

    TibiaInfoSpecification<T> equal(String field, Optional<? extends Object> optional) {
        if (optional.isPresent()) {
            Predicate equalPredicate = builder.equal(
                    root.get(field),
                    optional.get()
            );

            addInstruction(equalPredicate);
        }

        return this;
    }


    TibiaInfoSpecification<T> like(String field, Optional<String> optional) {
        if (optional.isPresent()) {
            Predicate likePredicate = builder.like(
                    builder.upper(root.get(field)),
                    optional.get().toUpperCase()
            );

            addInstruction(likePredicate);
        }

        return this;
    }

    TibiaInfoSpecification<T> and() {
        operator = BooleanOperator.AND;

        return this;
    }

    TibiaInfoSpecification<T> or() {
        operator = BooleanOperator.OR;

        return this;
    }

    TibiaInfoSpecification<T> endInstruction() {
        predicate = builder.and(predicate, currentPredicate);

        currentPredicate = null;
        operator = null;

        return this;
    }

}
