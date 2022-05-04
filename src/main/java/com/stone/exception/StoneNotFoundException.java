package com.stone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StoneNotFoundException extends RuntimeException{

    public StoneNotFoundException(){}

    public StoneNotFoundException(String message){super(message);}

    public StoneNotFoundException(String message,Throwable cause){super(message,cause);}
}
