package com.anurag.projects.airBnbApp.Exceptions;

public class ResourceNotFoundException extends RuntimeException{


    public ResourceNotFoundException(String message) {
        super(message);
    }
}
