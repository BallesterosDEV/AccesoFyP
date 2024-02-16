package com.proyectoacceso02ev.persistence.models;

import com.proyectoacceso02ev.dto.Rol;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "T_USERS")
@Data
@NoArgsConstructor
public class User implements Serializable, UserDetails {

    @Column(name = "C_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "C_USERNAME", unique = true, nullable = false)
    private String username;

    @Column(name = "C_EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "C_PASSWORD", nullable = false)
    private String password;

    @Column(name = "C_DESCRIPTION")
    private String description;

    @Column(name = "C_CREATED_DATE", nullable = false)
    private LocalDate createdDate;

    @Column(name = "C_PUBLICATIONS")
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Publication> publications;

    @Column(name = "C_FOLLOWERS")
    @ManyToMany
    @JoinTable(
            name = "T_USER_FOLLOWERS",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "FOLLOWER_ID")
    )
    private List<User> followers;

    @Column(name = "C_FOLLOWED")
    @ManyToMany(mappedBy = "followers")
    private List<User> followed;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permiso", referencedColumnName = "rol_id")
    Rol permise;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
