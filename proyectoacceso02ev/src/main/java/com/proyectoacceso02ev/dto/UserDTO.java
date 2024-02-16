package com.proyectoacceso02ev.dto;

import com.proyectoacceso02ev.persistence.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String email;
    private String description;
    private LocalDate createdDate;
    private List<PublicationDTO> publications;
    String rolname;

    public UserDTO(String username) {
        this.username = username;
    }


    public UserDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.description = user.getDescription();
        this.createdDate = user.getCreatedDate();
        this.rolname = user.getPermise().getRolName();
    }


    public List<PublicationDTO> getPublications() {
        return publications;
    }

    public void setPublications(List<PublicationDTO> publications) {
        this.publications = publications;
    }
}
