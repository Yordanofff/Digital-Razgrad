package com.example.homework11.Repositories;

import com.example.homework11.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User getUserByUsername(String username);
    User getUserByEmail(String email);
}
