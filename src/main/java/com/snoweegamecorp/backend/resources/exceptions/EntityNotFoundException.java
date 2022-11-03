package com.snoweegamecorp.backend.resources.exceptions;

public class EntityNotFoundException extends RuntimeException{
    private static final long serialVerionUID = 1L;

    public EntityNotFoundException(String msg){
        super(msg);
    }
}
