package com.hypermarket.springbootproject.demo.exception;

public class UserWithGivenEmailAlreadyExistsException extends RuntimeException{

public UserWithGivenEmailAlreadyExistsException(String message){super(message); }
}
