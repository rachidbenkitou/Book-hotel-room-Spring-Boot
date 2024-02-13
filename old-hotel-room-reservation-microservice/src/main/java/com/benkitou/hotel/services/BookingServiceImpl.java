package com.benkitou.hotel.services;

import com.benkitou.hotel.criteria.BookingCriteria;
import com.benkitou.hotel.daos.BlackListClientRepository;
import com.benkitou.hotel.daos.BookingRepository;
import com.benkitou.hotel.daos.BookingStatusRepository;
import com.benkitou.hotel.daos.RoomBookingRepository;
import com.benkitou.hotel.dtos.ClientDto;
import com.benkitou.hotel.dtos.RoomDto;
import com.benkitou.hotel.dtos.bookingdtos.BookingDto;
import com.benkitou.hotel.dtos.bookingdtos.BookingRequestDto;
import com.benkitou.hotel.dtos.bookingdtos.BookingSumPricePerYearDTO;
import com.benkitou.hotel.dtos.bookingdtos.RoomBookingRequestDto;
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

import java.util.ArrayList;
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
    private final BlackListClientRepository blackListClientRepository;

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

        try {
            if (blackListClientRepository.existsByClientIdAndHotelIdAndIsAllowed(bookingRequestDto.getClientId(), bookingRequestDto.getHotelId(), false)) {
                throw new IllegalArgumentException(String.format("The client with the id %d is not allowed to make reservation for the hotel %d.", bookingRequestDto.getClientId(), bookingRequestDto.getHotelId()));
            }
            List<RoomBookingRequestDto> roomBookingRequestDtos = bookingRequestDto.getRoomBookingRequest();
            if (Objects.isNull(roomBookingRequestDtos) || roomBookingRequestDtos.isEmpty()) {
                throw new IllegalArgumentException("At least one room booking is required for the booking.");
            }
            ClientDto clientDto = clientService.getClientById(bookingRequestDto.getClientId());
            bookingRequestDto.setStatusId(BookingStatusIds.IN_PROGRESS);
            BookingStatus bookingStatus = bookingStatusRepository.findById(bookingRequestDto.getStatusId())
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
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while making booking.", e);
        }
    }

    private Booking retrieveBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityServiceException(String.format("The booking with id %d not found.", bookingId)));
    }

    @Override
    public BookingDto modifyBookingStatusToActive(Long bookingId) {
        try {
            Booking booking = retrieveBookingById(bookingId);
            booking.setStatusId(BookingStatusIds.ACTIVE);
            return bookingMapper.modelToDto(bookingRepository.save(booking));
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while modifying booking status.", e);

        }
    }

    @Override
    public BookingDto modifyBookingStatusToCancelled(Long bookingId) {
        try {
            Booking booking = retrieveBookingById(bookingId);
            booking.setStatusId(BookingStatusIds.CANCELLED);
            return bookingMapper.modelToDto(bookingRepository.save(booking));
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while modifying booking status.", e);
        }
    }

    @Override
    public Long countReservationsByHotelId(Long hotelId) {
        try {
            return bookingRepository.countByHotelId(hotelId);
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while counting reservations by hotelId.", e);
        }
    }

    @Override
    public Double sumReservationsPriceByHotelId(Long hotelId) {
        try {
            if (bookingRepository.sumPriceByHotelId(hotelId) == null || bookingRepository.sumPriceByHotelId(hotelId) == 0.0) {
                return 0.0;
            }
            return bookingRepository.sumPriceByHotelId(hotelId);
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while calculating sum reservations price by hotelId.", e);
        }
    }

    @Override
    public List<BookingSumPricePerYearDTO> sumReservationsPriceForEveryYearByHotelId(Long hotelId) {
        try {
            List<BookingSumPricePerYearDTO> dtos = new ArrayList<>();
            List<Object[]> results = bookingRepository.sumReservationPriceByYear(hotelId);

            for (Object[] result : results) {
                Integer year = (Integer) result[0];
                Double totalPrice = (Double) result[1];

                BookingSumPricePerYearDTO dto = BookingSumPricePerYearDTO.builder()
                        .year(year)
                        .totalPrice(totalPrice)
                        .build();
                dtos.add(dto);
            }

            return dtos;
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while calculating sum reservations price  for every year by hotelId.", e);
        }
    }

}
