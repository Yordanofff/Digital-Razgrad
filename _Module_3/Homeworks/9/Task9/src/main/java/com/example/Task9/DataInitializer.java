package com.example.Task9;

import com.example.Task9.Brand.Brand;
import com.example.Task9.Brand.BrandRepository;
import com.example.Task9.Brand.BrandService;
import com.example.Task9.Brand.Country.Country;
import com.example.Task9.Brand.Country.CountryRepository;
import com.example.Task9.Clothes.Clothes;
import com.example.Task9.Clothes.ClothesRepository;
import com.example.Task9.Clothes.ClothesService;
import com.example.Task9.Clothes.Type.ClothType;
import com.example.Task9.Clothes.Type.ClothTypeRepository;
import com.example.Task9.Clothes.Size.ClothesSize;
import com.example.Task9.Clothes.Size.ClothesSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private ClothesSizeRepository clothesSizeRepository;

    @Autowired
    private ClothTypeRepository clothTypeRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private BrandService brandService;
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ClothesRepository clothesRepository;

    @Autowired
    private ClothesService clothesService;

    @Override
    public void run(String... args) throws Exception {

        List<String> sizeNames = Arrays.asList("S", "M", "L", "XL", "XXL");
        populateSizes(sizeNames);

        List<String> typeNames = Arrays.asList("Shirts", "Pants", "Jackets");
        populateNames(typeNames);

        List<String> countryNames = Arrays.asList("Bulgaria", "Italy", "France", "USA", "China");
        populateCountries(countryNames);

        Optional<Brand> optionalBrand = brandRepository.findBrandByName("Test brand 1");
        if (optionalBrand.isEmpty()){
            brandService.addBrand("Test brand 1", 1900, "Bulgaria");
            brandService.addBrand("Test brand 2", 2000, "Italy");
            brandService.addBrand("Test brand 3", 2001, "Japan");
        }

        Optional<Clothes> optionalClothes = clothesRepository.findClothByName("Test item 1");
        if (optionalClothes.isEmpty()){
            clothesService.addCloth("Test item 1","Test brand 1", 1L,"Shirts", 11.22, 2);
            clothesService.addCloth("Test item 2","Test brand 2", 2L,"Dress", 2.0, 0);
            clothesService.addCloth("Test item 3(null brand)","This will be Null", 99L,"Dress", 22.02, 4);
        }
    }

    private void populateSizes(List<String> sizeNames) {
        for (String sizeName : sizeNames) {
            Optional<ClothesSize> optionalClothesSize = clothesSizeRepository.findByName(sizeName);
            if (optionalClothesSize.isEmpty()) {
                ClothesSize clothesSize = new ClothesSize();
                clothesSize.setName(sizeName);
                clothesSizeRepository.save(clothesSize);
            }
        }
    }

    private void populateNames(List<String> typeNames) {
        for (String typeName : typeNames) {
            Optional<ClothType> optionalClothType = clothTypeRepository.findClothTypeByName(typeName);
            if (optionalClothType.isEmpty()) {
                ClothType clothType = new ClothType();
                clothType.setName(typeName);
                clothTypeRepository.save(clothType);
            }
        }
    }

    private void populateCountries(List<String> countryNames) {
        for (String countryName : countryNames) {
            Optional<Country> optionalCountry = countryRepository.findByName(countryName);
            if (optionalCountry.isEmpty()) {
                Country country = new Country();
                country.setName(countryName);
                countryRepository.save(country);
            }
        }
    }

}
