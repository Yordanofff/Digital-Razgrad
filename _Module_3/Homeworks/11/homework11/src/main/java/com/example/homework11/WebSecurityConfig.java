package com.example.homework11;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                                .requestMatchers("/", "/movies", "/login").permitAll()
//                        .requestMatchers("/admin").hasRole("ADMIN")
//                                .requestMatchers("/countries/**", "/actors/**", "/genres/**").hasAuthority("ROLE_ADMIN")
//                        .requestMatchers("/user").hasRole("USER")
                                .requestMatchers("/movies/**").hasAuthority("ROLE_USER")
//                                .requestMatchers("/user_and_admin").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
//                                .anyRequest().authenticated()
                                .anyRequest().hasAuthority("ROLE_ADMIN")
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .usernameParameter("username_or_email")  // this is needed in the html form.
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }
}
