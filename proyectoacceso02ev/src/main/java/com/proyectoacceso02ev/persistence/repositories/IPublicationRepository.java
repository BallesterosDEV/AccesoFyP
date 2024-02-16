package com.proyectoacceso02ev.persistence.repositories;

import com.proyectoacceso02ev.persistence.models.Publication;
import com.proyectoacceso02ev.persistence.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPublicationRepository extends JpaRepository<Publication, Long> {
    List<Publication> findByAuthor(User user);
}
