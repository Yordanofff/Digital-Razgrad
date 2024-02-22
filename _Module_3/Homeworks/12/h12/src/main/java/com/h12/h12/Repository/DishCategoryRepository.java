package com.h12.h12.Repository;

import com.h12.h12.Entity.DishCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishCategoryRepository extends JpaRepository<DishCategory, Long> {
    Optional<DishCategory> findByName(String name);
}
