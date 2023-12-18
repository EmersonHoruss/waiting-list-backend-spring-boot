package com.ownprojects.backend.DTOs.exceptions;


import lombok.Getter;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class ExceptionDTO {
    private final Date timestamp;
    private String message;
    private final String path;
    private List<SubExceptionDTO> errors;

    public ExceptionDTO(Exception ex, HttpServletRequest request) {
        this(request);
        this.message = ex.getMessage();
        initErrors(ex);
    }

    public ExceptionDTO(String message, HttpServletRequest request){
        this(request);
        this.message=message;
    }

    public ExceptionDTO(HttpServletRequest request){
        timestamp = new Date();
        path = request.getRequestURI();
    }

    private void initErrors(Exception ex) {
        errors = new ArrayList<SubExceptionDTO>();
        if (ex instanceof MethodArgumentNotValidException) {
            List<ObjectError> objectErrors = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
            for (ObjectError objectError : objectErrors) {
                errors.add(new SubExceptionDTO(objectError));
            }
        } else {
            errors = null;
        }
    }
}
