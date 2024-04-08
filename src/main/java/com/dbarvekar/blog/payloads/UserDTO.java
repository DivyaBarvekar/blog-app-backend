package com.dbarvekar.blog.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    @NotEmpty
    @Size(min = 4, message = "UserName must be min of 4 characters")
    private String name;

    @Email(message = "Email address is not valid !!")
    private String email;

    @NotEmpty
    @Size(min = 8, message = "password must be min of 8 characters")
    private String password;

    @NotEmpty
    private String about;
}
