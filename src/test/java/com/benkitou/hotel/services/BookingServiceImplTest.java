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
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.mappers.BookingMapper;
import com.benkitou.hotel.services.inter.ClientService;
import com.benkitou.hotel.services.inter.RoomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {
    @Mock
    BookingMapper bookingMapper ;


    @Test
    public void testMakeBookingSuccess() throws EntityNotFoundException {

        // Arrange
        BookingRepository bookingRepository = mock(BookingRepository.class);
        RoomService roomService = mock(RoomService.class);
        ClientService clientService = mock(ClientService.class);
        RoomBookingRepository roomBookingRepository = mock(RoomBookingRepository.class);
        BookingStatusRepository bookingStatusRepository = mock(BookingStatusRepository.class);
        BookingServiceImpl bookingService = new BookingServiceImpl(
                bookingRepository,
                bookingMapper,
                roomService,
                clientService,
                roomBookingRepository,
                bookingStatusRepository
        );

        BookingRequestDto bookingRequestDto = createValidBookingRequestDto();

        // Mock service method calls
        when(clientService.getClientById(anyLong())).thenReturn(createValidClientDto());
        when(bookingStatusRepository.findById(anyLong())).thenReturn(Optional.of(createValidBookingStatus()));
        when(roomService.getRoomById(anyLong())).thenReturn(createValidRoomDto());
        // Mock the BookingMapper
        when(bookingMapper.modelToDto(any())).thenAnswer(invocation -> {
            Booking bookingArgument = invocation.getArgument(0);
            // Assuming you have a method in BookingMapper to convert Booking to BookingDto
            return new BookingDto(
                    bookingArgument.getId(),
                    bookingArgument.getClientId(),
                    "SampleClientPhone",
                    "sample.client@example.com",
                    bookingArgument.getStatusId(),
                    "SampleStatusName",
                    bookingArgument.getPrice(),
                    bookingArgument.getDateCreated()
            );
        });


        // Act
        BookingDto result = bookingService.makeBooking(bookingRequestDto);


        assertThat(result.getClientId()).isEqualTo(bookingRequestDto.getClientId());

        // Assert
        // Add assertions based on the expected behavior after a successful booking
        // For example, verify that the repository's save method was called with the correct arguments
        verify(bookingRepository, times(1)).save(any());
        assertThat(result).isNotNull();

    }

    private BookingRequestDto createValidBookingRequestDto() {
        // Assuming you have a Builder pattern for BookingRequestDto
        return BookingRequestDto.builder()
                .clientId(1L)
                .statusId(1L)
                .price(100)
                .dateCreated(LocalDate.now())
                .roomBookingRequest(Collections.singletonList(createValidRoomBookingRequestDto()))
                .build();
    }


    private RoomBookingRequestDto createValidRoomBookingRequestDto() {
        // Assuming you have a Builder pattern for RoomBookingRequestDto
        return RoomBookingRequestDto.builder()
                .roomId(1L)
                .numberOfDays(3)
                .price(50.0)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(3))
                .build();
    }

    private ClientDto createValidClientDto() {
        // Assuming you have a Builder pattern for ClientDto
        return new ClientDto(1L, "John", "Doe", "123 Main St", "555-1234", "john.doe@example.com");
    }

    private BookingStatus createValidBookingStatus() {
        // Assuming you have a Builder pattern for BookingStatus
        return new BookingStatus(1L, "Confirmed");
    }

    private RoomDto createValidRoomDto() {
        // Assuming you have a constructor in RoomDto that takes the specified parameters
        return new RoomDto(1L, 101, 2, 100.0, 1L, "Sample Hotel");
    }

}