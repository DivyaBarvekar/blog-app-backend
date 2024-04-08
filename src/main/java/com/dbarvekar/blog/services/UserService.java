package com.dbarvekar.blog.services;

import com.dbarvekar.blog.payloads.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO create(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();

    boolean deleteUser(Long id);
}
