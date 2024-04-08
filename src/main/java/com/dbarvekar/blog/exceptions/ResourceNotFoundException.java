package com.dbarvekar.blog.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    String message;
    public ResourceNotFoundException(String message){
        super(message);
    }
}
