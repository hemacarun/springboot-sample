package com.pollmgmt.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler {


    @ExceptionHandler(value={CustomAuthenticationException.class})
    public ResponseEntity<Object> handleCustomAuthenticationException(Exception ex, WebRequest request){

        System.out.println("-------inside  the CustomAuthenticationException any exception----");
        ex.printStackTrace();
        ErrorMessage errorMessage= new ErrorMessage(301, ex.toString(), new Date());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @ExceptionHandler(value={MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request){

        System.out.println("-------inside  the handleMethodArgumentNotValidException any exception----");
        //ex.printStackTrace();
        System.out.println(ex.getFieldError());
        System.out.println(ex.getFieldErrors());
        System.out.println(ex.getMessage());
        ErrorMessage errorMessage= new ErrorMessage(301,
                ex.getMessage(),
                new Date());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){

        System.out.println("-------inside  the handleAnyException any exception----");
        ex.printStackTrace();
        ErrorMessage errorMessage= new ErrorMessage(301, ex.toString(), new Date());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);

    }




}
