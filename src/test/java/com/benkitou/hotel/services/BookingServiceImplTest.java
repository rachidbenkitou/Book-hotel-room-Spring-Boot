package com.benkitou.hotel.services;

import com.benkitou.hotel.daos.BookingRepository;
import com.benkitou.hotel.daos.BookingStatusRepository;
import com.benkitou.hotel.daos.RoomBookingRepository;
import com.benkitou.hotel.dtos.ClientDto;
import com.benkitou.hotel.dtos.RoomDto;
import com.benkitou.hotel.dtos.bookingdtos.BookingDto;
import com.benkitou.hotel.dtos.bookingdtos.BookingRequestDto;
import com.benkitou.hotel.dtos.bookingdtos.RoomBookingRequestDto;
import com.benkitou.hotel.entities.BookingStatus;
import com.benkitou.hotel.entities.RoomBooking;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.mappers.BookingMapper;
import com.benkitou.hotel.services.inter.ClientService;
import com.benkitou.hotel.services.inter.RoomService;
import com.benkitou.hotel.utils.BookingStatusIds;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private RoomService roomService;

    @Mock
    private ClientService clientService;

    @Mock
    private RoomBookingRepository roomBookingRepository;

    @Mock
    private BookingStatusRepository bookingStatusRepository;

    @Mock
    private BookingMapper bookingMapper;

    @InjectMocks
    private BookingServiceImpl bookingService;


    // Test for successful booking
    @Test
    void testMakeBookingSuccess() throws EntityNotFoundException {
        // Arrange
        BookingRequestDto bookingRequestDto = createValidBookingRequestDto();
        when(clientService.getClientById(anyLong())).thenReturn(createValidClientDto());
        when(bookingStatusRepository.findById(anyLong())).thenReturn(Optional.of(createValidBookingStatus()));
        when(roomService.getRoomById(anyLong())).thenReturn(createValidRoomDto());
        when(bookingMapper.modelToDto(any())).thenReturn(createValidBookingDto());


        // Create a list to capture saved RoomBooking entities
        List<RoomBooking> capturedRoomBookings = new ArrayList<>();

        // Mock the roomBookingRepository to capture saved RoomBooking entities
        doAnswer(invocation -> {
            RoomBooking roomBookingArgument = invocation.getArgument(0);
            capturedRoomBookings.add(roomBookingArgument);
            return null; // void method, so return null
        }).when(roomBookingRepository).save(any(RoomBooking.class));

        // Act
        BookingDto result = bookingService.makeBooking(bookingRequestDto);

        // Assert
        // Verify that the client ID in the result matches the client ID in the request
        assertThat(result.getClientId()).isEqualTo(bookingRequestDto.getClientId());
        // Verify that the status ID in the result is set to IN_PROGRESS
        assertThat(result.getStatusId()).isEqualTo(BookingStatusIds.IN_PROGRESS);
        // Verify that the price in the result matches the price in the request
        assertThat(result.getPrice()).isEqualTo(bookingRequestDto.getPrice());
        // Verify that the bookingRepository's save method was called once
        verify(bookingRepository, times(1)).save(any());
        // Verify that the result is not null
        assertThat(result).isNotNull();

        // Assert the number of saved RoomBooking entities
        assertThat(capturedRoomBookings).hasSize(bookingRequestDto.getRoomBookingRequest().size());
    }

    // Test when the room list is empty, should throw IllegalArgumentException
    @Test
    void testMakeBookingThrowIllegalArgumentExceptionWhenEmptyRoomList() {
        // Arrange
        BookingRequestDto bookingRequestDto = createBookingRequestDtoWithRoomBookingRequestEmpty();

        // Act and Assert
        // Verify that an IllegalArgumentException is thrown with the specified message
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> bookingService.makeBooking(bookingRequestDto))
                .withMessage("At least one room booking is required for the booking.");
    }

    // Test when BookingStatus does not exist, should throw EntityNotFoundException
    @Test
    void testMakeBookingEntityNotFoundExceptionWhenBookingStatusDoesNotExist() {
        // Arrange
        BookingRequestDto bookingRequestDto = createValidBookingRequestDto();
        when(bookingStatusRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act and Assert
        // Verify that an EntityNotFoundException is thrown with the specified message
        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> bookingService.makeBooking(bookingRequestDto))
                .withMessage("BookingStatus not found with id: " + bookingRequestDto.getStatusId());
    }

    private BookingRequestDto createValidBookingRequestDto() {
        return BookingRequestDto.builder()
                .clientId(1L)
                .statusId(1L)
                .price(100)
                .dateCreated(LocalDate.now())
                .roomBookingRequest(Collections.singletonList(createValidRoomBookingRequestDto()))
                .build();
    }

    private BookingRequestDto createBookingRequestDtoWithRoomBookingRequestEmpty() {
        return BookingRequestDto.builder()
                .clientId(1L)
                .statusId(1L)
                .price(100)
                .dateCreated(LocalDate.now())
                .roomBookingRequest(Collections.emptyList())
                .build();
    }

    private RoomBookingRequestDto createValidRoomBookingRequestDto() {
        return RoomBookingRequestDto.builder()
                .roomId(1L)
                .numberOfDays(3)
                .price(50.0)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(3))
                .build();
    }

    private ClientDto createValidClientDto() {
        return new ClientDto(1L, "John", "Doe", "123 Main St", "555-1234", "john.doe@example.com", "Test");
    }

    private BookingStatus createValidBookingStatus() {
        return new BookingStatus(1L, "Confirmed");
    }

    private RoomDto createValidRoomDto() {
        return new RoomDto(1L, 101, 2, 100.0, 1L, "Sample Hotel");
    }

    private BookingDto createValidBookingDto() {
        return new BookingDto(1L, 1L, "0655407529", "rachid@example.com", 1L, "Delivered", 100, LocalDate.now());
    }
}
