package com.example.homework11;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenderRepository extends JpaRepository<Gender, Long> {
    Optional<Gender> findByName(String name);
}
