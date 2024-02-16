package com.proyectoacceso02ev.dto;

import com.proyectoacceso02ev.persistence.models.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Rol implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    Long rolId;

    @Column(name = "rol_nombre")
    String rolName;

    @OneToMany(mappedBy = "permise", cascade = CascadeType.ALL)
    List<User> users;
}
