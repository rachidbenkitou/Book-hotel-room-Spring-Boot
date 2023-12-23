package com.benkitou.hotel.config;

import com.benkitou.hotel.daos.CityRepository;
import com.benkitou.hotel.entities.City;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CityRepository cityRepository;

    private void cityInitializer(){
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
    @Override
    public void run(String... args) {
        cityInitializer();
    }
}
