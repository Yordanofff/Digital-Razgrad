package com.h12.h12.Repository;

import com.h12.h12.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {
    User getUserByUsername(@Param("username") String username);
    User getUserByEmail(@Param("email") String email);
}
