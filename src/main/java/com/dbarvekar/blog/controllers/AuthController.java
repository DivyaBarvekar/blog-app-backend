//package com.dbarvekar.blog.controllers;
//
//import com.dbarvekar.blog.payloads.JWTAuthRequest;
//import com.dbarvekar.blog.payloads.JWTAuthResponse;
//import com.dbarvekar.blog.repositories.UserRepository;
//import com.dbarvekar.blog.security.JWTTokenHelper;
//import com.dbarvekar.blog.services.UserService;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/v1/auth")
//public class AuthController {
//
//    @Autowired
//    JWTTokenHelper jwtTokenHelper;
//
//    @Autowired
//    UserDetailsService userDetailsService;
//
//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    ModelMapper modelMapper;
//
//    @PostMapping("/login")
//    public ResponseEntity<JWTAuthResponse> createToken(@RequestBody JWTAuthRequest request) throws Exception {
//        this.authenticate(request.getUserName(), request.getPassword());
//        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUserName());
//        String token = this.jwtTokenHelper.generateTokenForUser(userDetails);
//
//        JWTAuthResponse response = new JWTAuthResponse();
//        response.setToken(token);
//        return new ResponseEntity<JWTAuthResponse>(response, HttpStatus.OK);
//    }
//
//    private void authenticate(String username, String password) throws Exception {
//
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
//                password);
//
//        try {
//
//            this.authenticationManager.authenticate(authenticationToken);
//
//        } catch (BadCredentialsException e) {
//            System.out.println("Invalid Detials !!");
//            throw new Exception("Invalid username or password !!");
//        }
//
//    }
//}
