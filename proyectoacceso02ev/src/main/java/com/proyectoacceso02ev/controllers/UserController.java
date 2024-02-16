package com.proyectoacceso02ev.controllers;

import com.proyectoacceso02ev.dto.UserDTO;
import com.proyectoacceso02ev.exceptions.ResourceNotFoundException;
import com.proyectoacceso02ev.services.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserServiceI userService;

    // Ver datos del usuario (no es un método obligatorio).
    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserDtoByUsername(@PathVariable String username) {
        UserDTO userDto = userService.getUserDtoByUsername(username);

        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("No se ha encontrado el usuario con el nombre " + username);
        }
    }

    // Editar descripción (privado)
    @PatchMapping("/{username}/editarDescripcion")
    public ResponseEntity<String> editarDescripcion(@PathVariable String username, @RequestBody String nuevaDescripcion) {
        boolean exito = userService.editarDescripcion(username, nuevaDescripcion);

        if (exito) {
            return new ResponseEntity<>("Descripción actualizada correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error al actualizar la descripción", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Buscar usuario por username (público)
    @GetMapping("/buscarUsuario/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        UserDTO userDto = userService.getUserDtoByUsername(username);

        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("No se ha encontrado el usuario con el nombre " + username);
        }
    }

    // Obtener todos los usuarios que siguen a un usuario (privado).
    @GetMapping("/{username}/followers")
    public ResponseEntity<List<UserDTO>> getFollowersByUsername(@PathVariable String username) {
        List<UserDTO> followers = userService.getFollowersByUsername(username);

        if (!followers.isEmpty()) {
            return new ResponseEntity<>(followers, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("No se han encontrado seguidores");
        }

    }


    // Obtener todos los usuarios a los que sigue un usuario (privado).
    @GetMapping("/{username}/following")
    public ResponseEntity<List<UserDTO>> getFollowing(@PathVariable String username) {
        List<UserDTO> following = userService.getFollowing(username);

        if (!following.isEmpty()) {
            return new ResponseEntity<>(following, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}