package com.benkitou.hotel.daos;

import com.benkitou.hotel.entities.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DiscountRepository extends JpaRepository<Discount, Long>, JpaSpecificationExecutor<Discount> {
    boolean existsByType(String type);
}
