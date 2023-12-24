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


    private void cityInitializer(){
        // delete items before adding
        cityRepository.deleteAll();
        // Define 20 Morocco cities
        List<String> moroccoCities = Arrays.asList(
                "Casablanca", "Rabat", "Marrakech", "Fes", "Tangier",
                "Agadir", "Essaouira", "Chefchaouen", "Meknes", "Ouarzazate",
                "Fez", "Oujda", "Kenitra", "Tetouan", "El Jadida",
                "Nador", "Beni Mellal", "Taza", "Settat", "Khouribga"
        );

        // Save cities to the database
        moroccoCities.forEach(cityName -> {
            City city = new City();
            city.setName(cityName);
            cityRepository.save(city);
        });
    }

    @Transactional
    public void bookingStatusInitializer() {
        // delete items before adding
        bookingStatusRepository.deleteAll();
        // Define booking status names
        List<String> statusNames = Arrays.asList("In Progress", "Canceled", "Active");

        // Save booking status entities to the database
        statusNames.forEach(statusName -> {
            BookingStatus bookingStatus = new BookingStatus();
            bookingStatus.setStatusName(statusName);
            bookingStatusRepository.save(bookingStatus);
        });
    }
    @Override
    public void run(String... args) {
        cityInitializer();
//        bookingStatusInitializer();
    }
}
