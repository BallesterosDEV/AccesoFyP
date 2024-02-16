package com.proyectoacceso02ev.controllers;

import com.proyectoacceso02ev.dto.PublicationDTO;
import com.proyectoacceso02ev.services.PublicationServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publications")
@CrossOrigin
public class PublicationController {

    @Autowired
    private PublicationServiceI publicationService;

    // Insertar publicación (privado)
    @PostMapping("/{username}")
    public ResponseEntity<String> insertarPublicacion(@PathVariable String username, @RequestBody PublicationDTO nuevaPublicacion) {
        boolean exito = publicationService.insertarPublicacion(username, nuevaPublicacion);

        if (exito) {
            return new ResponseEntity<>("Publicación insertada correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error al insertar la publicación", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Editar publicación (privado)
    @PatchMapping("/{username}/{idPublicacion}")
    public ResponseEntity<String> editarPublicacion(
            @PathVariable String username,
            @PathVariable long idPublicacion,
            @RequestBody PublicationDTO publicacionActualizada
    ) {
        boolean exito = publicationService.editarPublicacion(username, idPublicacion, publicacionActualizada);

        if (exito) {
            return new ResponseEntity<>("Publicación editada correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error al editar la publicación", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Borrar publicación (privado)
    @DeleteMapping("/{username}/{publicationId}")
    public ResponseEntity<String> eliminarPublicacion(@PathVariable String username, @PathVariable Long publicationId) {
        boolean exito = publicationService.eliminarPublicacion(username, publicationId);

        if (exito) {
            return new ResponseEntity<>("Publicación eliminada correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error al eliminar la publicación", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todas las publicaciones (Privado)
    @GetMapping("{username}")
    public ResponseEntity<List<PublicationDTO>> getPublicationsByUsername(@PathVariable String username) {
        List<PublicationDTO> publications = publicationService.getPublicationsByUsername(username);

        if (!publications.isEmpty()) {
            return new ResponseEntity<>(publications, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Obtener todas las publicaciones de de un usuario (público)
    @GetMapping("/author/{authorUsername}")
    public ResponseEntity<List<PublicationDTO>> getPublicationsByAuthorUsername(
            @PathVariable String authorUsername) {
        List<PublicationDTO> publications = publicationService.getPublicationsByAuthorUsername(authorUsername);

        if (!publications.isEmpty()) {
            return new ResponseEntity<>(publications, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Obtener todas las publicaciones de los usuarios que sigues (privado)

    @GetMapping("/followed/{username}")
    public ResponseEntity<List<PublicationDTO>> getPublicationsByFollowed(@PathVariable String username) {
        List<PublicationDTO> publications = publicationService.getPublicationsByFollowed(username);

        if (!publications.isEmpty()) {
            return new ResponseEntity<>(publications, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
