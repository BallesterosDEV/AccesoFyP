package com.proyectoacceso02ev.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) // Maneja todas las excepciones generales
    public ResponseEntity<String> handleException(Exception ex) {
        // Construye la respuesta con un mensaje de error personalizado
        String errorMessage = "Ocurrió un error inesperado: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class) // Maneja la excepción específica ResourceNotFoundException
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        // Construye la respuesta con un mensaje de error personalizado
        String errorMessage = "El recurso solicitado no fue encontrado: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
