package com.proyectoacceso02ev.persistence.repositories;

import com.proyectoacceso02ev.persistence.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String username);
}
