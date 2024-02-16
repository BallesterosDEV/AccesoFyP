package com.proyectoacceso02ev.services;

import com.proyectoacceso02ev.dto.PublicationDTO;
import com.proyectoacceso02ev.persistence.models.Publication;

import java.util.List;

public interface PublicationServiceI {
    public List<PublicationDTO> convertPublicationsToDTO(List<Publication> publications);

    public boolean insertarPublicacion(String username, PublicationDTO nuevaPublicacion);

    public boolean editarPublicacion(String username, long idPublicacion, PublicationDTO publicacionActualizada);

    public boolean eliminarPublicacion(String username, Long publicationId);

    public List<PublicationDTO> getPublicationsByUsername(String username);

    public List<PublicationDTO> getPublicationsByAuthorUsername(String authorUsername);

    public List<PublicationDTO> getPublicationsByFollowed(String username);
}
