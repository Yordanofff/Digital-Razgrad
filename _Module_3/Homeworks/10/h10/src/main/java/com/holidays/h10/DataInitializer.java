package com.holidays.h10;

import com.holidays.h10.Resort.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ResortTypeRepository resortTypeRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private ResortRepository resortRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private ResortService resortService;

    @Override
    public void run(String... args) throws Exception {
        List<String> resortTypes = Arrays.asList("Зимен", "Летен", "Пролетен", "Няма данни");
        populateTypes(resortTypes);

        List<String> countryNames = Arrays.asList("България", "Италия", "Франция");
        populateCountries(countryNames);

        List<String> resortNamesBG = Arrays.asList("Банско", "Велинград", "София", "Пампорово", "Боровец", "Хисаря");
        populateResorts(resortNamesBG, "България");

        List<String> resortNamesFR = Arrays.asList("Courchevel", "chamonix-mont-blanc", "Saint-Tropez");
        populateResorts(resortNamesFR, "Франция");

        List<String> resortNamesIT = Arrays.asList("Cortina d'Ampezzo", "Portofino", "Capri");
        populateResorts(resortNamesIT, "Италия");

        Optional<Hotel> optionalHotel = hotelRepository.findByName("Хотел Банско");
        if (optionalHotel.isEmpty()) {
            Resort resort1 = resortRepository.findByName("Банско").orElse(null);
            Hotel hotel1 = new Hotel(resort1, "Хотел Банско", 30, 3, true);
            hotelRepository.save(hotel1);
        }

    }

    private void populateResorts(List<String> resortNames, String countryName) {
        Optional<Country> optionalCountry = countryRepository.findByName(countryName);
        if (optionalCountry.isPresent()) {
            Country country = optionalCountry.get();
            populateResorts(resortNames, country);
        }
    }

    private void populateResorts(List<String> resortNames, Country country) {
        for (String resortName : resortNames) {
            Optional<Resort> optionalResort = resortRepository.findByName(resortName);
            if (optionalResort.isEmpty()) {  // TODO: country check?
                resortService.addResort(resortName, country, null);
            }
        }
    }

    private void populateCountries(List<String> countryNames) {
        for (String countryName : countryNames) {
            Optional<Country> optionalCountry = countryRepository.findByName(countryName);
            if (optionalCountry.isEmpty()) {
                Country country = new Country(countryName);
                countryRepository.save(country);
            }
        }
    }

    private void populateTypes(List<String> resortTypes) {
        for (String resortTypeName : resortTypes) {
            Optional<ResortType> optionalResortType = resortTypeRepository.findByName(resortTypeName);
            if (optionalResortType.isEmpty()) {
                ResortType resortType = new ResortType(resortTypeName);
                resortTypeRepository.save(resortType);
            }
        }
    }


}
