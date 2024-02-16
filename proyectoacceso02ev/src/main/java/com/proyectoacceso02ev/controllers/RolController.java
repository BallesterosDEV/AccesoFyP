package com.proyectoacceso02ev.controllers;


import com.proyectoacceso02ev.dto.Rol;
import com.proyectoacceso02ev.services.RolServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin
public class RolController {

    @Autowired
    RolServiceI rolMngmnt;

    @PostMapping("/insert")
    void insertRol(@RequestBody Rol rol) {
        rolMngmnt.saveRol(rol);
    }
}
