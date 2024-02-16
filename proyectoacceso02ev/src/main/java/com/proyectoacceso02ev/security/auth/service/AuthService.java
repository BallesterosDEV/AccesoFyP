package com.proyectoacceso02ev.security.auth.service;


import com.proyectoacceso02ev.dto.Rol;
import com.proyectoacceso02ev.persistence.models.User;
import com.proyectoacceso02ev.persistence.repositories.IUserRepository;
import com.proyectoacceso02ev.persistence.repositories.RolRepositoryI;
import com.proyectoacceso02ev.security.auth.modeldto.AuthResponseDTO;
import com.proyectoacceso02ev.security.auth.modeldto.LoginRequestDTO;
import com.proyectoacceso02ev.security.auth.modeldto.RegisterRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AuthService implements AuthServiceI {

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private RolRepositoryI rolRepo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepo.findByUsername(request.getUsername()).orElseThrow();
        return new AuthResponseDTO(jwtService.getToken(user));
    }


    public AuthResponseDTO register(RegisterRequestDTO request) {
        Optional<Rol> rol = rolRepo.findById(2L);
        if (rol.isPresent()) {
            User user = new User();
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setCreatedDate(LocalDate.now());
            user.setPermise(rol.get());
            userRepo.save(user);
            return new AuthResponseDTO(jwtService.getToken(user));
        } else {
            return new AuthResponseDTO("");
        }

    }
}
