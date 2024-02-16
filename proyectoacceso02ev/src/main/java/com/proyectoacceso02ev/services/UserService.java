package com.proyectoacceso02ev.services;

import com.proyectoacceso02ev.dto.PublicationDTO;
import com.proyectoacceso02ev.dto.UserDTO;
import com.proyectoacceso02ev.persistence.models.User;
import com.proyectoacceso02ev.persistence.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserServiceI {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PublicationService publicationService;


    public UserDTO getUserDtoByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();

        if (user != null) {
            UserDTO userDto = new UserDTO();
            userDto.setUsername(user.getUsername());
            userDto.setEmail(user.getEmail());
            userDto.setDescription(user.getDescription());
            userDto.setCreatedDate(user.getCreatedDate());
            List<PublicationDTO> publicationDTOs = publicationService.convertPublicationsToDTO(user.getPublications());
            userDto.setPublications(publicationDTOs);
            return userDto;
        } else {
            return null;
        }
    }

    public boolean editarDescripcion(String username, String nuevaDescripcion) {
        User user = userRepository.findByUsername(username).orElseThrow();

        if (user != null) {
            user.setDescription(nuevaDescripcion);
            userRepository.save(user);
            return true;
        } else {
            return false; // Usuario no encontrado
        }
    }

    public List<UserDTO> getFollowersByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();

        if (user != null) {
            List<User> followers = user.getFollowers();
            return convertUsersToDTO(followers);
        } else {
            return Collections.emptyList();
        }
    }

    public List<UserDTO> convertUsersToDTO(List<User> users) {
        List<UserDTO> userDTOs = new ArrayList<>();

        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTO.setDescription(user.getDescription());
            userDTO.setCreatedDate(user.getCreatedDate());
            // Puedes agregar más campos según tus necesidades

            userDTOs.add(userDTO);
        }

        return userDTOs;
    }

    public List<UserDTO> getFollowing(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();

        if (user != null) {
            List<User> following = user.getFollowed();
            return convertUsersToDTO(following);
        } else {
            return Collections.emptyList();
        }
    }

}
