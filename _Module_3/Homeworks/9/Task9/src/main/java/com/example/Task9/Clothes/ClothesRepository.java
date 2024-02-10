package com.example.Task9.Clothes;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClothesRepository extends CrudRepository<Clothes, Long> {
    Optional<Clothes> findClothByName(String name);
}
