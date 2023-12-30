package com.benkitou.hotel.daos;

import com.benkitou.hotel.dtos.BlackListClientDto;
import com.benkitou.hotel.entities.BlacklistClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlackListClientRepository extends JpaRepository<BlacklistClient, Long> {

    @Query("SELECT NEW com.benkitou.hotel.dtos.BlackListClientDto(blClient.id, blClient.hotelId, blClient.cin, blClient.clientId, blClient.phone, blClient.isAllowed, blClient.numberOfTries) " +
            "FROM BlacklistClient blClient " +
            "WHERE (:id IS NULL OR blClient.id = :id) " +
            "AND (:clientId IS NULL OR blClient.clientId = :clientId) " +
            "AND (:isAllowed IS NULL OR blClient.isAllowed = :isAllowed) " +
            "AND (:numberOfTries IS NULL OR blClient.numberOfTries = :numberOfTries) " +
            "AND (:phone IS NULL OR LOWER(blClient.phone) LIKE LOWER(CONCAT('%', :phone, '%'))) " +
            "AND (:cin IS NULL OR LOWER(blClient.cin) LIKE LOWER(CONCAT('%', :cin, '%'))) " +
            "AND (:email IS NULL OR blClient.hotelId= :hotelId)")
    List<BlackListClientDto> findBlackListClientsByQuery(
            @Param("id") Long id,
            @Param("hotelId") Long hotelId,
            @Param("phone") String phone,
            @Param("cin") String cin,
            @Param("isAllowed") Boolean isAllowed,
            @Param("numberOfTries") Integer numberOfTries,
            @Param("clientId") Long clientId
    );

    boolean existsByCinAndHotelId(String cin, Long hotelId);

    boolean existsByClientIdAndHotelId(Long clientId, Long hotelId);
}
