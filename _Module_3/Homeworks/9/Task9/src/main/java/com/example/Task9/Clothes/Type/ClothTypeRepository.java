package com.example.Task9.Clothes.Type;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClothTypeRepository extends CrudRepository<ClothType, Long> {
    Optional<ClothType> findClothTypeByName(String name);
}
