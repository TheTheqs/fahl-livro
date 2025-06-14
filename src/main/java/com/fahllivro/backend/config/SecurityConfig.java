package com.fahllivro.backend.config;

import com.fahllivro.backend.security.AuthUserDetailsService;
import com.fahllivro.backend.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthUserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configure(http))
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Auth e criação de usuário são públicos
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()

                        // Livros - GETs públicos
                        .requestMatchers(HttpMethod.GET, "/books/**").permitAll()

                        // Livros - criação, edição e exclusão requerem autenticação
                        .requestMatchers(HttpMethod.POST, "/books").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/books/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/books/**").authenticated()

                        // Chamadas - qualquer um pode criar e visualizar
                        .requestMatchers(HttpMethod.POST, "/chamadas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/chamadas/**").permitAll()

                        // Rota protegida para /users/**
                        .requestMatchers("/users/**").authenticated()

                        // Qualquer outra rota precisa de autenticação
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
