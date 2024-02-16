package com.proyectoacceso02ev.security.auth.service;


import com.proyectoacceso02ev.security.auth.modeldto.AuthResponseDTO;
import com.proyectoacceso02ev.security.auth.modeldto.LoginRequestDTO;
import com.proyectoacceso02ev.security.auth.modeldto.RegisterRequestDTO;

public interface AuthServiceI {
    AuthResponseDTO login(LoginRequestDTO request);

    AuthResponseDTO register(RegisterRequestDTO request);
}
