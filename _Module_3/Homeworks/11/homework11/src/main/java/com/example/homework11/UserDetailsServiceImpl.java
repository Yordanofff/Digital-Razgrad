package com.example.homework11;

import com.example.homework11.Entities.User;
import com.example.homework11.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadUserByUsernameOrEmail(username);
    }


    private UserDetails loadUserByName(String username) throws UsernameNotFoundException{
        User user = userRepository.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user);
    }


    private UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user);
    }

    private UserDetails loadUserByUsernameOrEmail(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(usernameOrEmail);
        if (user != null) {
            return new MyUserDetails(user);
        }

        user = userRepository.getUserByEmail(usernameOrEmail);
        if (user != null) {
            return new MyUserDetails(user);
        }

        throw new UsernameNotFoundException("Could not find user");
    }
}
