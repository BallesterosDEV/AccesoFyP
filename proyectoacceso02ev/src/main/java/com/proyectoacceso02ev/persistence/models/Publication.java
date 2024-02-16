package com.proyectoacceso02ev.persistence.models;

import com.proyectoacceso02ev.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "T_PUBLICATIONS")
@Data
@NoArgsConstructor
public class Publication implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_ID", unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "C_AUTHOR", nullable = false)
    private User author;

    @Column(name = "C_TEXT", nullable = false)
    private String text;

    @Column(name = "C_CREATED_DATE", nullable = false)
    private LocalDate createdDate;

    @Column(name = "C_EDITED_DATE")
    private LocalDate editedDate;

    public UserDTO getAuthorDto() {
        UserDTO userDto = new UserDTO();
        userDto.setUsername(author.getUsername());
        return userDto;
    }
}
