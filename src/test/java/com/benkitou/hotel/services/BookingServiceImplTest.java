package com.benkitou.hotel.services;

import com.benkitou.hotel.daos.BookingRepository;
import com.benkitou.hotel.daos.BookingStatusRepository;
import com.benkitou.hotel.daos.RoomBookingRepository;
import com.benkitou.hotel.dtos.ClientDto;
import com.benkitou.hotel.dtos.RoomDto;
import com.benkitou.hotel.dtos.bookingDtos.BookingDto;
import com.benkitou.hotel.dtos.bookingDtos.BookingRequestDto;
import com.benkitou.hotel.dtos.bookingDtos.RoomBookingRequestDto;
import com.benkitou.hotel.entities.Booking;
import com.benkitou.hotel.entities.BookingStatus;
import com.benkitou.hotel.entities.RoomBooking;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.mappers.BookingMapper;
import com.benkitou.hotel.services.inter.BookingService;
import com.benkitou.hotel.services.inter.ClientService;
import com.benkitou.hotel.services.inter.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @InjectMocks
    private BookingServiceImpl bookingService;
    @Mock
    private BookingMapper bookingMapper;

    @Mock
    private ClientService clientService;

    @Mock
    private BookingStatusRepository bookingStatusRepository;

    @Mock
    private RoomService roomService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private RoomBookingRepository roomBookingRepository;

    @BeforeEach
    void setUp() {

    }


    @Test
    void makeBooking_SuccessfulBooking() throws EntityNotFoundException {
        // Arrange
        BookingRequestDto bookingRequestDto = createValidBookingRequestDto();
        when(clientService.getClientById(anyLong())).thenReturn(createValidClientDto());
        when(bookingStatusRepository.findById(anyLong())).thenReturn(Optional.of(createValidBookingStatus()));
        when(roomService.getRoomById(anyLong())).thenReturn(createValidRoomDto());

        // Act
        BookingDto result = bookingService.makeBooking(bookingRequestDto);

        // Assert
        verify(bookingRepository, times(1)).save(any());
        verify(roomBookingRepository, times(bookingRequestDto.getRoomBookingRequest().size())).save(any());

        assertNotNull(result);
        assertEquals(bookingRequestDto.getClientId(), result.getClientId());
        assertEquals(bookingRequestDto.getStatusId(), result.getStatusId());
        assertEquals(bookingRequestDto.getDateCreated(), result.getDateCreated());
        assertEquals(bookingRequestDto.getPrice(), result.getPrice());
    }


    public static BookingRequestDto createValidBookingRequestDto() {
        BookingRequestDto bookingRequestDto = new BookingRequestDto();
        bookingRequestDto.setClientId(1L);
        bookingRequestDto.setStatusId(1L);
        bookingRequestDto.setPrice(1200);
        bookingRequestDto.setDateCreated(LocalDate.now());
        bookingRequestDto.setRoomBookingRequest(Collections.singletonList(createValidRoomBookingRequestDto()));
        return bookingRequestDto;
    }

    public static BookingRequestDto createBookingRequestDtoWithoutRoomBookings() {
        BookingRequestDto bookingRequestDto = new BookingRequestDto();
        bookingRequestDto.setClientId(1L);
        bookingRequestDto.setStatusId(1L);
        bookingRequestDto.setPrice(1200);
        bookingRequestDto.setDateCreated(LocalDate.now());
        return bookingRequestDto;
    }

    public static ClientDto createValidClientDto() {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);
        // Set other client attributes as needed
        return clientDto;
    }

    public static BookingStatus createValidBookingStatus() {
        BookingStatus bookingStatus = new BookingStatus();
        bookingStatus.setId(1L);
        bookingStatus.setStatusName("Active");
        return bookingStatus;
    }

    public static RoomDto createValidRoomDto() {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(1L);
        // Set other room attributes as needed
        return roomDto;
    }

    public static Booking createValidBookingEntity() {
        // Set other booking attributes as needed
        return Booking.builder()
                .clientId(1L)
                .StatusId(1L)
                .dateCreated(LocalDate.now())
                .price(1200)
                .build();
    }

    public static RoomBookingRequestDto createValidRoomBookingRequestDto() {
        RoomBookingRequestDto roomBookingRequestDto = new RoomBookingRequestDto();
        roomBookingRequestDto.setRoomId(1L);
        roomBookingRequestDto.setNumberOfDays(3);
        roomBookingRequestDto.setPrice(300);
        roomBookingRequestDto.setStartDate(LocalDate.now());
        return roomBookingRequestDto;
    }

    public static RoomBooking createValidRoomBookingEntity() {
        // Set other room booking attributes as needed
        return RoomBooking.builder()
                .roomId(1L)
                .bookingId(1L)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(3))
                .numberOfDays(3)
                .build();
    }

    // Add more helper methods as needed for other entities

    public static ClientService createMockClientService() throws EntityNotFoundException {
        ClientService mockClientService = mock(ClientService.class);
        when(mockClientService.getClientById(1L)).thenReturn(createValidClientDto());
        return mockClientService;
    }

    public static BookingStatusRepository createMockBookingStatusRepository() {
        BookingStatusRepository mockRepository = mock(BookingStatusRepository.class);
        when(mockRepository.findById(1L)).thenReturn(java.util.Optional.of(createValidBookingStatus()));
        return mockRepository;
    }

    public static RoomService createMockRoomService() throws EntityNotFoundException {
        RoomService mockRoomService = mock(RoomService.class);
        when(mockRoomService.getRoomById(1L)).thenReturn(createValidRoomDto());
        return mockRoomService;
    }
}