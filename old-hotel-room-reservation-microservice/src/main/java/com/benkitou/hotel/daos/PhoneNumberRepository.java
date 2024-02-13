package com.benkitou.hotel.daos;

import com.benkitou.hotel.entities.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
    boolean existsPhoneNumberByPhone(String phone);
}
