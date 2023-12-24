package com.benkitou.hotel.daos;

import com.benkitou.hotel.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    boolean existsEmailByEmail(String email);
}
