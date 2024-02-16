package com.proyectoacceso02ev.services;


import com.proyectoacceso02ev.dto.Rol;
import com.proyectoacceso02ev.persistence.repositories.RolRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements RolServiceI {

    @Autowired
    RolRepositoryI rolRepo;


    @Override
    public void saveRol(Rol rol) {
        rolRepo.save(rol);
    }
}
