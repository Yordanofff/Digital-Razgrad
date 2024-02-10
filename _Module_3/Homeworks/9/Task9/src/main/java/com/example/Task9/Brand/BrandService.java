package com.example.Task9.Brand;

import com.example.Task9.Brand.Country.Country;
import com.example.Task9.Brand.Country.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BrandService {
    private final CountryRepository countryRepository;
    private final BrandRepository brandRepository;

    public BrandService(CountryRepository countryRepository,
                        BrandRepository brandRepository) {
        this.countryRepository = countryRepository;
        this.brandRepository = brandRepository;
    }

    public Brand addBrand(String name, int year, String countryName) {
        Brand brand = new Brand(name, year, null);

        if (countryName != null) {
            Country country = findOrCreateCountry(countryName);
            brand.setCountry(country);
        }

        brandRepository.save(brand);
        return brand;
    }

    public Country findOrCreateCountry(String countryName) {
        Optional<Country> optionalCountry = countryRepository.findByName(countryName);
        Country country;
        if (optionalCountry.isPresent()) {
            country = optionalCountry.get();
        } else {
            country = new Country(countryName);
            countryRepository.save(country);
        }
        return country;
    }
}
