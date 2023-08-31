package com.hypermarket.springbootproject.demo.exception;

public class UserWithGivenUsernameAlreadyExistsException extends RuntimeException{

    public UserWithGivenUsernameAlreadyExistsException(String message){super(message); }
}
