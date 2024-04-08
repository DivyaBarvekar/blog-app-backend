package com.dbarvekar.blog.controllers;

import com.dbarvekar.blog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dbarvekar.blog.payloads.UserDTO;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //POST create user
    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        UserDTO savedUserDTO = userService.create(userDTO);
        return new ResponseEntity<>(savedUserDTO, HttpStatus.CREATED);
    }

    //PUT update user
    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO){
        UserDTO savedUserDTO = userService.update(userDTO);
        return ResponseEntity.ok(savedUserDTO);
    }

    //GET all users
    @GetMapping("/get-all-users")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    //GET user by id
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable("id") Long id){
        UserDTO savedUserDTO = userService.getUserById(id);
        return new ResponseEntity<>(savedUserDTO, HttpStatus.OK);
    }

    //DELETE user
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }

}
