package com.fahllivro.backend.controller;

import com.fahllivro.backend.dto.AuthRequestDTO;
import com.fahllivro.backend.dto.AuthResponseDTO;
import com.fahllivro.backend.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthRequestDTO request) {
        var authToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha());
        authenticationManager.authenticate(authToken);

        String jwt = jwtService.generateToken(request.getEmail());
        return ResponseEntity.ok(new AuthResponseDTO(jwt));
    }
}
