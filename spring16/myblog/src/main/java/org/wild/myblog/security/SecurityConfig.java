package org.wild.myblog.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.wild.myblog.service.CustomUserDetailsService;

@Configuration
//@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Value("${cors.allowed-origin}")
    private String allowedOrigin;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, CustomUserDetailsService customUserDetailsService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.customUserDetailsService = customUserDetailsService;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/articles/**").permitAll() // Autoriser tous les utilisateurs à lire les articles
                        .requestMatchers(HttpMethod.POST, "/articles/**").hasRole("ADMIN") // Seuls les admins peuvent créer des articles
                        .requestMatchers(HttpMethod.PUT, "/articles/**").hasRole("ADMIN") // Seuls les admins peuvent mettre à jour des articles
                        .requestMatchers(HttpMethod.DELETE, "/articles/**").hasRole("ADMIN") // Seuls les admins peuvent supprimer des articles
                        .requestMatchers("/auth/**").permitAll() // Permettre l'accès public aux endpoints sous /auth/
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Accessible uniquement aux administrateurs
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") // Accessible aux utilisateurs et administrateurs
                        .anyRequest().authenticated() // Tous les autres endpoints nécessitent une authentification
                )
                .userDetailsService(customUserDetailsService)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
