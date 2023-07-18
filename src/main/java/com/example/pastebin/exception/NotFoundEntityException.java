package com.example.pastebin.exception;

public class NotFoundEntityException extends RuntimeException {
    public NotFoundEntityException(String message) {
        super(message);
    }
}
