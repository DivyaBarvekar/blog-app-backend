package com.dbarvekar.blog.impl;

import com.dbarvekar.blog.entities.User;
import com.dbarvekar.blog.exceptions.ResourceNotFoundException;
import com.dbarvekar.blog.repositories.UserRepository;
import com.dbarvekar.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dbarvekar.blog.payloads.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserDTO create(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        user = userRepository.save(user);
        return convertToDTO(user);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {

        User user = new User();
        user = userRepository.findById(userDTO.getId()).orElseThrow(() -> {
            return new ResourceNotFoundException("User not found with id %d".formatted(userDTO.getId()));
        });

        User user1 = convertToEntity(userDTO);
        BeanUtils.copyProperties(user1,user,"id");

        user = userRepository.save(user);
        return convertToDTO(user);

    }

    @Override
    public UserDTO getUserById(Long id) {
         Optional<User> user = userRepository.findById(id);
         if(user.isPresent()){
             return convertToDTO(user.get());
         }
         throw new ResourceNotFoundException("User not found with id %d".formatted(id));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = userList.stream().map(user -> {
            return convertToDTO(user);
        }).collect(Collectors.toList());
        return userDTOList;
    }

    @Override
    public boolean deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> {return new ResourceNotFoundException("User not found with id %d".formatted(id));});
        userRepository.deleteById(id);
        return true;
    }

    private User convertToEntity(UserDTO userDTO){

        User user = modelMapper.map(userDTO, User.class);
        //use modelmapper instead of copyProperties
//        BeanUtils.copyProperties(userDTO, user);
        return user;

    }

    private UserDTO convertToDTO(User user){

        UserDTO userDTO = modelMapper.map(user,UserDTO.class);
//        BeanUtils.copyProperties(user, userDTO);
        return userDTO;

    }
}
