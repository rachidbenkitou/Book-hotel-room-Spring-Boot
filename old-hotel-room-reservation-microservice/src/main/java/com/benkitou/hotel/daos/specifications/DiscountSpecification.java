package com.benkitou.hotel.daos.specifications;

import com.benkitou.hotel.criteria.DiscountCriteria;
import com.benkitou.hotel.entities.Discount;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class DiscountSpecification implements Specification<Discount> {
    private final DiscountCriteria discountCriteria;

    @Override
    public Predicate toPredicate(Root<Discount> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (discountCriteria.getId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("id"), discountCriteria.getId()));
        }
        if (discountCriteria.getType() != null) {
            predicates.add(criteriaBuilder.equal(root.get("type"), discountCriteria.getType()));
        }
        if (discountCriteria.getIsActive() != null) {
            predicates.add(criteriaBuilder.equal(root.get("isActive"), discountCriteria.getIsActive()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
