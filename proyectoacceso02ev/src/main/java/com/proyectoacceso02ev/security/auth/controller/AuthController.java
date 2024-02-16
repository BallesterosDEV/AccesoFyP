package com.proyectoacceso02ev.security.auth.controller;


import com.proyectoacceso02ev.security.auth.modeldto.AuthResponseDTO;
import com.proyectoacceso02ev.security.auth.modeldto.LoginRequestDTO;
import com.proyectoacceso02ev.security.auth.modeldto.RegisterRequestDTO;
import com.proyectoacceso02ev.security.auth.service.AuthServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthServiceI authService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }

}