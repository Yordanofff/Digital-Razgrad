package com.holidays.h10.Resort;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ResortTypeRepository extends CrudRepository<ResortType, Long> {
    Optional<ResortType> findByName(String name);
}
