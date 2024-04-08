package com.dbarvekar.blog.payloads;

import lombok.Data;

@Data
public class JWTAuthResponse {
    private String token;
    private String user;
}
