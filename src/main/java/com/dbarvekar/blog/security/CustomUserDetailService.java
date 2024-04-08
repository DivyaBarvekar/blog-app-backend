//package com.dbarvekar.blog.security;
//
//import com.dbarvekar.blog.entities.User;
//import com.dbarvekar.blog.exceptions.ResourceNotFoundException;
//import com.dbarvekar.blog.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public class CustomUserDetailService implements UserDetailsService {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        // load user from database by username
//
//        User user = userRepository.findByEmail(username).orElseThrow(()-> {return new ResourceNotFoundException("User not found with username %d".formatted(username));});
//        return user;
//    }
//}
