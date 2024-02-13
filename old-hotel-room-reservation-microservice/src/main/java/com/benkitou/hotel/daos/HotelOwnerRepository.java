package com.benkitou.hotel.daos;

import com.benkitou.hotel.dtos.HotelOwnerDto;
import com.benkitou.hotel.entities.HotelOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelOwnerRepository extends JpaRepository<HotelOwner, Long> {

    @Query("SELECT NEW com.benkitou.hotel.dtos.HotelOwnerDto(owner.id, owner.firstName, owner.lastName, owner.address, owner.phone, owner.email, owner.cin) " +
            "FROM HotelOwner owner " +
            "WHERE (:id IS NULL OR owner.id = :id) " +
            "AND (:phone IS NULL OR LOWER(owner.phone) LIKE LOWER(CONCAT('%', :phone, '%'))) " +
            "AND (:cin IS NULL OR LOWER(owner.cin) LIKE LOWER(CONCAT('%', :cin, '%'))) " +
            "AND (:email IS NULL OR LOWER(owner.email) LIKE LOWER(CONCAT('%', :email, '%')))")
    List<HotelOwnerDto> findHotelOwnersByQuery(
            @Param("id") Long id,
            @Param("phone") String phone,
            @Param("email") String email,
            @Param("cin") String cin
    );

    boolean existsHotelOwnerById(Long id);

    boolean existsHotelOwnersByEmail(String email);

    boolean existsHotelOwnersByPhone(String phone);

}
