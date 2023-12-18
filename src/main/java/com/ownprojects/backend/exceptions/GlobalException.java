package com.ownprojects.backend.exceptions;

import com.ownprojects.backend.DTOs.exceptions.ExceptionDTO;
import com.ownprojects.backend.constants.EntityConstraints;
import com.ownprojects.backend.utils.translator.Translator;
import org.apache.tomcat.util.bcel.Const;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Constraint;
import java.util.Arrays;

@ControllerAdvice
public class GlobalException {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            DuplicateKeyException.class,
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class,
            MissingRequestHeaderException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class
    })
    @ResponseBody
    public ResponseEntity<ExceptionDTO> badRequest(Exception ex, HttpServletRequest request) {
        ExceptionDTO exDTO = new ExceptionDTO(ex, request);
        ex.printStackTrace();
        return new ResponseEntity<ExceptionDTO>(exDTO, HttpStatus.BAD_REQUEST);
    }

    /*@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            ResourceNotFoundException.class
    })
    @ResponseBody
    public ResponseEntity<ExceptionDTO> notFoundRequest(Exception ex, HttpServletRequest request){
        System.out.println("resource" + ex.getMessage());
        ExceptionDTO exDTO = new ExceptionDTO(ex, request);
        ex.printStackTrace();
        return new ResponseEntity<ExceptionDTO>(exDTO, HttpStatus.NOT_FOUND);
    }*/

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            ConstraintViolationException.class,
            DataIntegrityViolationException.class
    })
    @ResponseBody
    public ResponseEntity<ExceptionDTO> constraintViolationException(Exception ex, HttpServletRequest request) {
        ConstraintViolationException constraintViolationException = (ConstraintViolationException) ex.getCause();
        String[] messageErrorsParts = constraintViolationException.getSQLException().getMessage().split("'");
        String[] constraint = messageErrorsParts[3].split("[.]");
        String table = constraint[0];
        String column = constraint[1].split(EntityConstraints.SEPARATOR)[1];
        String columnEs = new Translator().getEs(column);
        String tableEs = new Translator().getEs(table);
        String messageError = "Ya existe un(a) " + tableEs + " con el(la) mismo " + columnEs;
        ExceptionDTO exDTO = new ExceptionDTO(messageError, request);
        return new ResponseEntity<ExceptionDTO>(exDTO, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            Exception.class
    })
    @ResponseBody
    public ResponseEntity<ExceptionDTO> fatalErrorUnexpectedError(Exception ex, HttpServletRequest request) {
        System.out.println("internal server error");
        ex.printStackTrace();
        ExceptionDTO exDTO = new ExceptionDTO(ex, request);
        return new ResponseEntity<ExceptionDTO>(exDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            NotFoundStaticValue.class
    })
    @ResponseBody
    public ResponseEntity<ExceptionDTO> notFoundStaticValue(Exception ex, HttpServletRequest request) {
        ExceptionDTO exDTO = new ExceptionDTO(ex, request);
        return new ResponseEntity<ExceptionDTO>(exDTO, HttpStatus.BAD_REQUEST);
    }



    /*
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    @RequestBody
    public ErrorMessage notFoundRequest(Exception ex, HttpServletRequest request){
        return new ErrorMessage(ex,request.getRequestURI());
    }
    */

    /*@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            BadRequestException.class,
            org.springframework.dao.DuplicateKeyException.class,
            org.springframework.web.HttpRequestMethodNotSupportedException.class,
            org.springframework.web.bind.MethodArgumentNotValidException.class,
            org.springframework.web.bind.MissingRequestHeaderException.class,
            org.springframework.web.bind.MissingServletRequestParameterException.class,
            org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class
    })
    @ResponseBody
    public ErrorMessage badRequest(Exception ex, HttpServletRequest request){
        return new ErrorMessage(ex,request.getRequestURI());
    }*/

    /*
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({ForbiddenException.class})
    @ResponseBody
    public ErrorMessage forbiddenRequest(Exception ex,HttpServletRequest request){
        return new ErrorMessage(ex, request.getRequestURI());
    }
    */

    /*
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({ConflictException.class})
    @ResponseBody
    public ErrorMessage conflict(Exception ex,HttpServletRequest request){
        return new ErrorMessage(ex, request.getRequestURI());
    }
    */

/*
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({
    UnauthorizedException.class,
    org.springframework.security.access.AccessDeniedException.class
    })
    @ResponseBody
    public ErrorMessage unauthorized(){

    }
    */

    /*
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ErrorMessage fatalErrorUnexpectedError(Exception ex,HttpServletRequest request){
        return new ErrorMessage(ex, request.getRequestURI());
    }
    */
}
