package com.example.Task9.Brand;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BrandRepository extends CrudRepository<Brand,Long> {
    Optional<Brand> findBrandByName(String name);
}
