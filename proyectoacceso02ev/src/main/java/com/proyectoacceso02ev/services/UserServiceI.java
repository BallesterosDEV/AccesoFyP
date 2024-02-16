package com.proyectoacceso02ev.services;

import com.proyectoacceso02ev.dto.UserDTO;
import com.proyectoacceso02ev.persistence.models.User;

import java.util.List;

public interface UserServiceI {
    public UserDTO getUserDtoByUsername(String username);

    public boolean editarDescripcion(String username, String nuevaDescripcion);

    public List<UserDTO> getFollowersByUsername(String username);

    public List<UserDTO> convertUsersToDTO(List<User> users);

    public List<UserDTO> getFollowing(String username);
}
