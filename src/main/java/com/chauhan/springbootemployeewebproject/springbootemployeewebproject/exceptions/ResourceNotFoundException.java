package com.chauhan.springbootemployeewebproject.springbootemployeewebproject.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
