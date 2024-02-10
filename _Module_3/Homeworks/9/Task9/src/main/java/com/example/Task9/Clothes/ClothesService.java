package com.example.Task9.Clothes;

import com.example.Task9.Brand.Brand;
import com.example.Task9.Brand.BrandRepository;
import com.example.Task9.Clothes.Size.ClothesSize;
import com.example.Task9.Clothes.Size.ClothesSizeRepository;
import com.example.Task9.Clothes.Type.ClothType;
import com.example.Task9.Clothes.Type.ClothTypeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClothesService {
    private final BrandRepository brandRepository;
    private final ClothTypeRepository clothTypeRepository;
    private final ClothesSizeRepository clothesSizeRepository;
    private final ClothesRepository clothesRepository;

    public ClothesService(BrandRepository brandRepository,
                          ClothTypeRepository clothTypeRepository,
                          ClothesSizeRepository clothesSizeRepository,
                          ClothesRepository clothesRepository) {
        this.brandRepository = brandRepository;
        this.clothTypeRepository = clothTypeRepository;
        this.clothesSizeRepository = clothesSizeRepository;
        this.clothesRepository = clothesRepository;
    }

    public Clothes addCloth(String name, String brandName, Long sizeId, String typeName, Double price, int quantity) {
        Clothes clothes = new Clothes();
        clothes.setName(name);
        clothes.setBrand(getBrand(brandName));
        clothes.setSize(getSize(sizeId));
        clothes.setType(getType(typeName));

        if (quantity == 0){
            // default value if not entered.
            quantity = 1;
        }
        clothes.setQuantity(quantity);

        clothes.setPrice(price);
        clothesRepository.save(clothes);
        return clothes;
    }

    private ClothType getType(String typeName) {
        Optional<ClothType> optionalClothType = clothTypeRepository.findClothTypeByName(typeName);
        ClothType clothType;
        if (optionalClothType.isPresent()) {
            clothType = optionalClothType.get();
        } else {
            clothType = new ClothType(typeName);
            clothTypeRepository.save(clothType);
        }
        return clothType;
    }

    private ClothesSize getSize(Long sizeId) {
        Optional<ClothesSize> optionalClothesSize = clothesSizeRepository.findById(sizeId);
        if (optionalClothesSize.isPresent()) {
            return optionalClothesSize.get();
        }
        return null;
    }

    private Brand getBrand(String brandName) {
        Optional<Brand> optionalBrand = brandRepository.findBrandByName(brandName);
        if (optionalBrand.isPresent()) {
            return optionalBrand.get();
        }
        return null;
    }
}
