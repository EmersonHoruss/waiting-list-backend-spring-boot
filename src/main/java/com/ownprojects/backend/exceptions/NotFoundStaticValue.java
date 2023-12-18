package com.ownprojects.backend.exceptions;

public class NotFoundStaticValue extends RuntimeException {
    public NotFoundStaticValue(String message){
        super(message);
    }
}
