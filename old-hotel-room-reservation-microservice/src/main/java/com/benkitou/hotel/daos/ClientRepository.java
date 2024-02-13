package com.benkitou.hotel.daos;

import com.benkitou.hotel.dtos.ClientDto;
import com.benkitou.hotel.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT NEW com.benkitou.hotel.dtos.ClientDto(client.id, client.firstName, client.lastName, client.address, client.phone, client.email, client.cin) " +
            "FROM Client client " +
            "WHERE (:id IS NULL OR client.id = :id) " +
            "AND (:phone IS NULL OR LOWER(client.phone) LIKE LOWER(CONCAT('%', :phone, '%'))) " +
            "AND (:cin IS NULL OR LOWER(client.cin) LIKE LOWER(CONCAT('%', :cin, '%'))) " +
            "AND (:email IS NULL OR LOWER(client.email) LIKE LOWER(CONCAT('%', :email, '%')))")
    List<ClientDto> findClientsByQuery(
            @Param("id") Long id,
            @Param("phone") String phone,
            @Param("email") String email,
            @Param("cin") String cin
    );

    boolean existsClientById(Long id);

    boolean existsClientByEmail(String email);

    boolean existsClientByPhone(String phone);

}
