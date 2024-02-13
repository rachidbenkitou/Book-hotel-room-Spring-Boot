package com.benkitou.hotel.daos.specifications;

import com.benkitou.hotel.criteria.RoomCriteria;
import com.benkitou.hotel.entities.Hotel;
import com.benkitou.hotel.entities.Room;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class RoomSpecification implements Specification<Room> {
    private final RoomCriteria roomCriteria;

    @Override
    public Predicate toPredicate(Root<Room> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        // Add your conditions based on RoomCriteria attributes
        if (roomCriteria.getId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("id"), roomCriteria.getId()));
        }
        if (roomCriteria.getNumber() != null) {
            predicates.add(criteriaBuilder.equal(root.get("number"), roomCriteria.getNumber()));
        }
        if (roomCriteria.getHotelId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("hotelId"), roomCriteria.getHotelId()));
        }
        if (roomCriteria.getRoomTypeId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("roomTypeId"), roomCriteria.getRoomTypeId()));
        }
        if (roomCriteria.getCapacity() != null) {
            predicates.add(criteriaBuilder.equal(root.get("capacity"), roomCriteria.getCapacity()));
        }
        if (roomCriteria.getCapacityAdults() != null) {
            predicates.add(criteriaBuilder.equal(root.get("capacityAdults"), roomCriteria.getCapacityAdults()));
        }
        if (roomCriteria.getCapacityChildren() != null) {
            predicates.add(criteriaBuilder.equal(root.get("capacityChildren"), roomCriteria.getCapacityChildren()));
        }
        if (roomCriteria.getIsAvailable() != null) {
            predicates.add(criteriaBuilder.equal(root.get("isAvailable"), roomCriteria.getIsAvailable()));
        }
        if (roomCriteria.getStartDate() != null && roomCriteria.getEndDate() != null) {
            predicates.add(criteriaBuilder.between(root.get("dateAvailable"), roomCriteria.getStartDate(), roomCriteria.getEndDate()));
        }
        if (roomCriteria.getStartPrice() != null && roomCriteria.getEndPrice() != null) {
            predicates.add(criteriaBuilder.between(root.get("price"), roomCriteria.getStartPrice(), roomCriteria.getEndPrice()));
        }
        if (roomCriteria.getHasWifi() != null) {
            predicates.add(criteriaBuilder.equal(root.get("hasWifi"), roomCriteria.getHasWifi()));
        }
        if (roomCriteria.getHasTv() != null) {
            predicates.add(criteriaBuilder.equal(root.get("hasTv"), roomCriteria.getHasTv()));
        }
        // Add condition for cityId by joining with the Hotel entity
        if (roomCriteria.getHotelCityId() != null) {
            Join<Room, Hotel> hotelJoin = root.join("hotel", JoinType.LEFT);
            predicates.add(criteriaBuilder.equal(hotelJoin.get("cityId"), roomCriteria.getHotelCityId()));
        }


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
