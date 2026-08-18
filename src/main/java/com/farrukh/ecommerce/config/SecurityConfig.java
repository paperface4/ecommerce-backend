package com.farrukh.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;
import com.farrukh.ecommerce.security.JwtAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

        .authorizeHttpRequests(auth -> auth

        .requestMatchers("/api/auth/register","/api/auth/login").permitAll()

        .requestMatchers(HttpMethod.PATCH,"/api/categories/**").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()

        .requestMatchers(HttpMethod.DELETE,"/api/categories/**").hasRole("ADMIN")

        .requestMatchers(HttpMethod.GET,"/api/products/**").permitAll()

        .requestMatchers(HttpMethod.POST,"/api/products/**").hasRole("ADMIN")

        .requestMatchers(HttpMethod.GET,"/api/categories/**").permitAll()

        
        .requestMatchers(HttpMethod.POST,"/api/categories/**").hasRole("ADMIN")

        .requestMatchers(HttpMethod.PATCH,"/api/products/**").hasRole("ADMIN")

        .requestMatchers(HttpMethod.DELETE,"/api/product/**").hasRole("ADMIN")

        .anyRequest().authenticated()
                )
                .addFilterBefore(
        jwtAuthenticationFilter,
        UsernamePasswordAuthenticationFilter.class
)

                .exceptionHandling(exception -> exception

                        .authenticationEntryPoint(
                                new HttpStatusEntryPoint(
                                        HttpStatus.UNAUTHORIZED
                                )
                        )

                        .accessDeniedHandler(
                                (request, response, accessDeniedException) -> {
                                    response.setStatus(
                                            HttpServletResponse.SC_FORBIDDEN
                                    );
                                }
                        )
                        
                );

        return http.build();
    }
}