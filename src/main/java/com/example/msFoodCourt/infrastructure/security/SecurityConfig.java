package com.example.msFoodCourt.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.msFoodCourt.domain.utils.constant.Constants.ROLE_ADMIN;
import static com.example.msFoodCourt.domain.utils.constant.Constants.ROLE_OWNER;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/restaurant/**").hasAuthority(ROLE_ADMIN)
                        .requestMatchers(HttpMethod.GET, "/restaurant/**","/home/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/dish/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/dish/**").hasAuthority(ROLE_OWNER)
                        .requestMatchers(HttpMethod.PUT, "/dish/**").hasAuthority(ROLE_OWNER)
                        .requestMatchers(HttpMethod.POST, "/restaurant-employee/**").hasAuthority(ROLE_OWNER)
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}