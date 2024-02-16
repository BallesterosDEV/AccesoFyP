package com.proyectoacceso02ev.persistence.repositories;


import com.proyectoacceso02ev.dto.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepositoryI extends JpaRepository<Rol, Long> {

}
