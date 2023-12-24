package com.benkitou.hotel.services.inter;

import com.benkitou.hotel.dtos.EmailDto;
import com.benkitou.hotel.dtos.PhoneNumberDto;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;

import java.util.List;

public interface EmailService {
    List<EmailDto> getEmails();

    EmailDto getEmailById(Long id) throws EntityNotFoundException;

    EmailDto addEmail(EmailDto emailDto) throws EntityAlreadyExistsException;

    EmailDto updateEmail(Long id, EmailDto emailDto) throws EntityNotFoundException, EntityAlreadyExistsException;

    ResponseDto deleteEmail(Long id) throws EntityAlreadyExistsException, EntityNotFoundException;
}
