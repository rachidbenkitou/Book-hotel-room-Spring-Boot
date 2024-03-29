package com.benkitou.hotel.services;

import com.benkitou.hotel.criteria.HotelCriteria;
import com.benkitou.hotel.daos.HotelRepository;
import com.benkitou.hotel.dtos.EmailDto;
import com.benkitou.hotel.dtos.HotelDto;
import com.benkitou.hotel.dtos.PhoneNumberDto;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.entities.Hotel;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.exceptions.EntityServiceException;
import com.benkitou.hotel.mappers.HotelMapper;
import com.benkitou.hotel.services.inter.EmailService;
import com.benkitou.hotel.services.inter.HotelService;
import com.benkitou.hotel.services.inter.ImageService;
import com.benkitou.hotel.services.inter.PhoneNumbersService;
import com.benkitou.hotel.services.strategy.inter.HotelImageStrategy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final ImageService imageService;
    private final PhoneNumbersService phoneNumbersService;
    private final EmailService emailService;

    //@Autowired
    //@Qualifier("hotelImageStrategy")
    private final HotelImageStrategy hotelImageStrategy;

    @Override
    public List<HotelDto> getHotels(HotelCriteria hotelCriteria) {
        try {
            return hotelRepository.findHotelsByQuery(
                    hotelCriteria.getId(),
                    hotelCriteria.getName(),
                    hotelCriteria.getAddress(),
                    hotelCriteria.getCityId(),
                    hotelCriteria.getStatus()
            );
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while retrieving hotels.", e);
        }
    }

    @Override
    public HotelDto getHotelById(Long id) throws EntityNotFoundException {

        try {
            HotelCriteria hotelCriteria = HotelCriteria.builder()
                    .id(id)
                    .build();

            List<HotelDto> hotelDtos = getHotels(hotelCriteria);
            return hotelDtos.stream()
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException(String.format("The hotel with the id %d is not found.", id)));
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while retrieving hotel.", e);
        }

    }

    private boolean isHotelExists(String name) {
        return hotelRepository.existsHotelsByName(name);
    }

    @Override
    public HotelDto addHotel(HotelDto hotelDto, List<MultipartFile> images) throws EntityAlreadyExistsException {
        hotelDto.setId(null);

        // Check if the hotel with the given name already exists
        throwExceptionIfExistsByName(hotelDto.getName());

        try {
            // Save the hotel and return the DTO
            Hotel savedHotel = hotelRepository.save(hotelMapper.dtoToModel(hotelDto));
            savedHotel.setDefaultEmail(hotelDto.getDefaultEmail());
            savedHotel.setDefaultPhoneNumber(hotelDto.getDefaultPhoneNumber());

            PhoneNumberDto phoneNumberDto = PhoneNumberDto.builder()
                    .phone(hotelDto.getDefaultPhoneNumber())
                    .hotelId(savedHotel.getId())
                    .build();
            EmailDto emailDto = EmailDto.builder()
                    .email(hotelDto.getDefaultEmail())
                    .hotelId(savedHotel.getId())
                    .build();

            phoneNumbersService.addPhoneNumber(phoneNumberDto);
            emailService.addEmail(emailDto);


            if (images != null) {
                savedHotel.setDefaultImage(images.get(0).getOriginalFilename());
                for (MultipartFile file : images) {
                    hotelImageStrategy.uploadImage(file, savedHotel.getId());
                }
            }


            return hotelMapper.modelToDto(savedHotel);

        } catch (Exception e) {
            // Handle any exceptions during the save process
            throw new EntityServiceException("An error occurred while saving the hotel.", e);
        }
    }


    private void throwExceptionIfExistsByName(String name) throws EntityAlreadyExistsException {
        if (isHotelExists(name)) {
            throw new EntityAlreadyExistsException(String.format("The hotel with name %s already exists.", name));
        }
    }

    @Override
    public HotelDto updateHotel(Long id, HotelDto hotelDto) throws EntityNotFoundException, EntityAlreadyExistsException {
        // Use Optional for clarity
        HotelDto existingHotel = Optional.ofNullable(getHotelById(id))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Hotel with ID %d not found.", id)));

        // Ensure ID consistency
        if (!existingHotel.getId().equals(hotelDto.getId())) {
            throw new IllegalArgumentException("The provided ID and DTO ID do not match.");
        }

        try {
            // Update the hotel and return the updated DTO
            Hotel updatedHotel = hotelRepository.save(hotelMapper.dtoToModel(hotelDto));
            return hotelMapper.modelToDto(updatedHotel);
        } catch (Exception e) {
            // Handle any exceptions during the update process
            throw new EntityServiceException("An error occurred while updating the hotel.", e);
        }
    }


    @Override
    public ResponseDto deleteHotel(Long id) throws EntityNotFoundException {
        if (!hotelRepository.existsHotelById(id)) {
            throw new EntityNotFoundException(String.format("Hotel with ID %d not found.", id));
        }
        try {
            hotelRepository.deleteById(id);
            return ResponseDto.builder()
                    .message("Hotel deleted successfully.")
                    .status(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while deleting the hotel.", e);
        }
    }

}
