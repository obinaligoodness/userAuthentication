package com.goodness.UserAuthenticationApp.exceptions;

public class BadCredentialsException extends Exception {
    public BadCredentialsException(String message){
        super(message);
    }
}