package com.benkitou.hotel.config;

import com.benkitou.hotel.daos.BookingStatusRepository;
import com.benkitou.hotel.daos.CityRepository;
import com.benkitou.hotel.entities.BookingStatus;
import com.benkitou.hotel.entities.City;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class DataInitializer implements CommandLineRunner {

    private final CityRepository cityRepository;
    private final BookingStatusRepository bookingStatusRepository;


    private void cityInitializer() {
        // delete items before adding
        cityRepository.deleteAll();

        // Define 20 Morocco cities with specified IDs
        List<Object[]> cityData = Arrays.asList(
                new Object[]{1L, "Casablanca"},
                new Object[]{2L, "Rabat"},
                new Object[]{3L, "Marrakech"},
                new Object[]{4L, "Fes"},
                new Object[]{5L, "Tangier"},
                new Object[]{6L, "Agadir"},
                new Object[]{7L, "Essaouira"},
                new Object[]{8L, "Chefchaouen"},
                new Object[]{9L, "Meknes"},
                new Object[]{10L, "Ouarzazate"},
                new Object[]{11L, "Fez"},
                new Object[]{12L, "Oujda"},
                new Object[]{13L, "Kenitra"},
                new Object[]{14L, "Tetouan"},
                new Object[]{15L, "El Jadida"},
                new Object[]{16L, "Nador"},
                new Object[]{17L, "Beni Mellal"},
                new Object[]{18L, "Taza"},
                new Object[]{19L, "Settat"},
                new Object[]{20L, "Khouribga"}
        );


        // Save cities to the database
        cityData.forEach(data -> {
            Long cityId = (Long) data[0];
            String cityName = (String) data[1];

            City city = City.builder()
                    .id(cityId)
                    .name(cityName)
                    .build();

            cityRepository.save(city);
        });
    }


    @Transactional
    public void bookingStatusInitializer() {
    // Define booking status names with specified IDs
        List<Object[]> statusData = Arrays.asList(
                new Object[]{1L, "In Progress"},
                new Object[]{2L, "Canceled"},
                new Object[]{3L, "Active"}
        );

    // Save booking status entities to the database
        statusData.forEach(status -> {
            BookingStatus bookingStatus = new BookingStatus();
            bookingStatus.setId((Long) status[0]);
            bookingStatus.setStatusName((String) status[1]);
            bookingStatusRepository.save(bookingStatus);
        });

    }
    @Override
    public void run(String... args) {
//        cityInitializer();
//        bookingStatusInitializer();
    }
}
