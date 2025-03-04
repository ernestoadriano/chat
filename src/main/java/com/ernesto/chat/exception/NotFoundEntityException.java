package com.ernesto.chat.exception;


public class NotFoundEntityException extends RuntimeException{

    public NotFoundEntityException() {
        super("Not founded!");
    }
}
