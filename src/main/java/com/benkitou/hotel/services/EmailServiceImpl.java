package com.benkitou.hotel.services;

import com.benkitou.hotel.daos.EmailRepository;
import com.benkitou.hotel.dtos.EmailDto;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.entities.Email;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.exceptions.EntityServiceException;
import com.benkitou.hotel.mappers.EmailMapper;
import com.benkitou.hotel.services.inter.EmailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final EmailRepository emailRepository;
    private final EmailMapper emailMapper;

    @Override
    public List<EmailDto> getEmails() {
        try {
            return emailMapper.modelsToDtos(emailRepository.findAll());
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while retrieving emails.", e);
        }
    }

    @Override
    public EmailDto getEmailById(Long id) throws EntityNotFoundException {
        try {
            Email email = emailRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(String.format("The email with the id %d is not found", id)));

            return emailMapper.modelToDto(email);
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while retrieving email.", e);
        }
    }

    @Override
    public EmailDto addEmail(EmailDto emailDto) throws EntityAlreadyExistsException {
        emailDto.setId(null);

        if (emailRepository.existsEmailByEmail(emailDto.getEmail())) {
            throw new EntityAlreadyExistsException(String.format("The email %s that exists in the hotel %d is already exists"
                    , emailDto.getEmail(), emailDto.getHotelId()));
        }
        try {
            Email savedEmail = emailRepository.save(emailMapper.dtoToModel(emailDto));
            return emailMapper.modelToDto(savedEmail);
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while storing the email.", e);
        }
    }

    @Override
    public EmailDto updateEmail(Long id, EmailDto emailDto) throws EntityNotFoundException, EntityAlreadyExistsException {
        EmailDto existingEmail = Optional.ofNullable(getEmailById(id))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Email with ID %d not found.", id)));

        if (!existingEmail.getId().equals(emailDto.getId())) {
            throw new IllegalArgumentException("The provided ID and DTO ID do not match.");
        }

        try {
            Email updatedEmail = emailRepository.save(emailMapper.dtoToModel(emailDto));
            return emailMapper.modelToDto(updatedEmail);
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while updating the email.", e);
        }
    }

    @Override
    public ResponseDto deleteEmail(Long id) throws EntityAlreadyExistsException, EntityNotFoundException {
        if (!emailRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format("Email with ID %d not found.", id));
        }
        try {
            emailRepository.deleteById(id);
            return ResponseDto.builder()
                    .message("Email deleted successfully.")
                    .status(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while deleting the email.", e);
        }
    }
}
