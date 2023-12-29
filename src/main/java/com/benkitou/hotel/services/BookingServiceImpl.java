package com.benkitou.hotel.services;

import com.benkitou.hotel.criteria.BookingCriteria;
import com.benkitou.hotel.daos.BookingRepository;
import com.benkitou.hotel.daos.BookingStatusRepository;
import com.benkitou.hotel.daos.RoomBookingRepository;
import com.benkitou.hotel.dtos.*;
import com.benkitou.hotel.dtos.bookingDtos.BookingDto;
import com.benkitou.hotel.dtos.bookingDtos.BookingRequestDto;
import com.benkitou.hotel.dtos.bookingDtos.RoomBookingRequestDto;
import com.benkitou.hotel.entities.Booking;
import com.benkitou.hotel.entities.BookingStatus;
import com.benkitou.hotel.entities.RoomBooking;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.exceptions.EntityServiceException;
import com.benkitou.hotel.mappers.BookingMapper;
import com.benkitou.hotel.services.inter.BookingService;
import com.benkitou.hotel.services.inter.ClientService;
import com.benkitou.hotel.services.inter.RoomService;
import com.benkitou.hotel.utils.BookingStatusIds;
import com.benkitou.hotel.utils.DateManipulator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final BookingMapper bookingMapper;

    private final RoomService roomService;
    private final ClientService clientService;

    private final RoomBookingRepository roomBookingRepository;
    private final BookingStatusRepository bookingStatusRepository;

    @Override
    public List<BookingDto> getAllBooking(BookingCriteria bookingCriteria) {
        try {
            return bookingRepository.findBookingsByQuery(
                    bookingCriteria.getId(),
                    bookingCriteria.getClientId(),
                    bookingCriteria.getStatusId(),
                    bookingCriteria.getPrice(),
                    bookingCriteria.getDateCreated()
            );
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while retrieving all booking.", e);
        }
    }

    @Override
    public BookingDto getBookingById(Long id) throws EntityNotFoundException {
        try {
            BookingCriteria bookingCriteria = BookingCriteria.builder()
                    .id(id)
                    .build();

            List<BookingDto> bookingDtos = getAllBooking(bookingCriteria);
            return bookingDtos.stream()
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException(String.format("The Booking with the id %d is not found.", id)));
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while retrieving booking.", e);
        }

    }

    @Override
    public BookingDto makeBooking(BookingRequestDto bookingRequestDto) throws EntityNotFoundException {
        List<RoomBookingRequestDto> roomBookingRequestDtos = bookingRequestDto.getRoomBookingRequest();
        if (Objects.isNull(roomBookingRequestDtos) || roomBookingRequestDtos.isEmpty()) {
            throw new IllegalArgumentException("At least one room booking is required for the booking.");
        }
        ClientDto clientDto= clientService.getClientById(bookingRequestDto.getClientId());
        bookingRequestDto.setStatusId(BookingStatusIds.IN_PROGRESS);
        BookingStatus bookingStatus= bookingStatusRepository.findById(bookingRequestDto.getStatusId())
                .orElseThrow(() -> new EntityNotFoundException("BookingStatus not found with id: " + bookingRequestDto.getStatusId()));

        // Create a new Booking entity
        Booking booking = Booking.builder()
                .clientId(clientDto.getId())
                .statusId(bookingStatus.getId())
                .dateCreated(bookingRequestDto.getDateCreated())
                .price(bookingRequestDto.getPrice())
                .build();

        // Save the Booking entity
        bookingRepository.save(booking);

        // Create RoomBooking entities for each selected room
        for (RoomBookingRequestDto roomBookingRequest : roomBookingRequestDtos) {
            RoomDto roomDto = roomService.getRoomById(roomBookingRequest.getRoomId());
            RoomBooking roomBooking = RoomBooking.builder()
                    .roomId(roomDto.getId())
                    .bookingId(booking.getId())
                    .startDate(roomBookingRequest.getStartDate())
                    .endDate(DateManipulator.addDaysToDate(roomBookingRequest.getStartDate(), roomBookingRequest.getNumberOfDays()))
                    .numberOfDays(roomBookingRequest.getNumberOfDays())
                    .build();


            // Save the RoomBooking entity
            roomBookingRepository.save(roomBooking);
        }
        return bookingMapper.modelToDto(booking);
    }

    private Booking retrieveBookingById(Long bookingId){
        return bookingRepository.findById(bookingId)
                .orElseThrow(()->new EntityServiceException(String.format("The booking with id %d not found.", bookingId)));
    }
    @Override
    public BookingDto modifyBookingStatusToActive(Long bookingId) {
        Booking booking = retrieveBookingById(bookingId);
        booking.setStatusId(BookingStatusIds.ACTIVE);
        return bookingMapper.modelToDto(bookingRepository.save(booking));
    }
    @Override
    public BookingDto modifyBookingStatusToCancelled(Long bookingId) {
        Booking booking = retrieveBookingById(bookingId);
        booking.setStatusId(BookingStatusIds.CANCELLED);
        return bookingMapper.modelToDto(bookingRepository.save(booking));
    }

}
