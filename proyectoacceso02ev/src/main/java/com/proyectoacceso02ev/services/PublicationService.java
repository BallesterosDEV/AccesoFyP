package com.proyectoacceso02ev.services;

import com.proyectoacceso02ev.dto.PublicationDTO;
import com.proyectoacceso02ev.persistence.models.Publication;
import com.proyectoacceso02ev.persistence.models.User;
import com.proyectoacceso02ev.persistence.repositories.IPublicationRepository;
import com.proyectoacceso02ev.persistence.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PublicationService implements PublicationServiceI {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IPublicationRepository publicationRepository;


    public List<PublicationDTO> convertPublicationsToDTO(List<Publication> publications) {
        List<PublicationDTO> publicationDTOs = new ArrayList<>();

        for (Publication publication : publications) {
            PublicationDTO publicationDTO = new PublicationDTO();
            publicationDTO.setId(publication.getId());
            publicationDTO.setText(publication.getText());

            publicationDTOs.add(publicationDTO);
        }

        return publicationDTOs;

    }

    public boolean insertarPublicacion(String username, PublicationDTO nuevaPublicacion) {
        Optional<User> author = userRepository.findByUsername(username);

        if (author != null) {
            Publication nuevaPublication = new Publication();
            nuevaPublication.setAuthor(author.orElseThrow());
            nuevaPublication.setText(nuevaPublicacion.getText());
            nuevaPublication.setCreatedDate(LocalDate.now());


            publicationRepository.save(nuevaPublication);


            return true;
        } else {
            return false;
        }
    }

    public boolean editarPublicacion(String username, long idPublicacion, PublicationDTO publicacionActualizada) {
        Optional<User> author = userRepository.findByUsername(username);
        Optional<Publication> optionalPublication = publicationRepository.findById(idPublicacion);

        if (author.isPresent() && optionalPublication.isPresent()) {
            Publication publication = optionalPublication.get();

            // Verifica que el autor de la publicación coincida con el usuario que intenta editar
            if (publication.getAuthor().equals(author.get())) {
                // Actualiza el texto de la publicación
                publication.setText(publicacionActualizada.getText());
                publication.setEditedDate(LocalDate.now());

                publicationRepository.save(publication);

                return true;
            }

        }

        return false;
    }


    public boolean eliminarPublicacion(String username, Long publicationId) {
        Optional<User> author = userRepository.findByUsername(username);

        if (author.isPresent()) {
            Optional<Publication> optionalPublication = publicationRepository.findById(publicationId);

            if (optionalPublication.isPresent()) {
                Publication publication = optionalPublication.get();

                // Verifica si el autor de la publicación es el usuario que intenta eliminarla
                if (publication.getAuthor().equals(author.get())) {
                    publicationRepository.delete(publication);
                    return true;
                }
            }
        }

        return false;
    }

    public List<PublicationDTO> getPublicationsByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user != null) {
            List<Publication> publications = publicationRepository.findByAuthor(user.orElseThrow());
            return convertPublicationsToDTO(publications);
        } else {
            return Collections.emptyList();
        }
    }

    public List<PublicationDTO> getPublicationsByAuthorUsername(String authorUsername) {
        Optional<User> author = userRepository.findByUsername(authorUsername);

        if (author != null) {
            List<Publication> publications = publicationRepository.findByAuthor(author.orElseThrow());
            return convertPublicationsToDTO(publications);
        } else {
            return Collections.emptyList();
        }
    }

    public List<PublicationDTO> getPublicationsByFollowed(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user != null) {
            List<User> followedUsers = user.get().getFollowed();
            List<PublicationDTO> publications = new ArrayList<>();

            for (User followedUser : followedUsers) {
                List<Publication> userPublications = followedUser.getPublications();
                List<PublicationDTO> userPublicationDTOs = convertPublicationsToDTO(userPublications);
                publications.addAll(userPublicationDTOs);
            }

            return publications;
        } else {
            return Collections.emptyList();
        }
    }
}
