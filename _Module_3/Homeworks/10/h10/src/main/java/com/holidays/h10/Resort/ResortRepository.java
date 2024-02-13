package com.holidays.h10.Resort;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResortRepository extends CrudRepository<Resort, Long> {
    Optional<Resort> findByName(String name);

    List<Resort> findByCountryId(Long countryId);
}
