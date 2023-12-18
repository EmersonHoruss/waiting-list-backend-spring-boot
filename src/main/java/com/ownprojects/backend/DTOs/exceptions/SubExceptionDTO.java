package com.ownprojects.backend.DTOs.exceptions;

import lombok.Getter;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Getter
public class SubExceptionDTO {
    private String field;
    private String message;

    public SubExceptionDTO(ObjectError objectError){
        field = ((FieldError) objectError).getField();
        message = objectError.getDefaultMessage();
    }
}
