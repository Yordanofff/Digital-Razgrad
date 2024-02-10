package com.example.Task9.Clothes.Size;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClothesSizeRepository extends CrudRepository<ClothesSize, Long> {
    Optional<ClothesSize> findByName(String sizeName);
}
