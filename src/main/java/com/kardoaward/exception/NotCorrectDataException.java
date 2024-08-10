package com.kardoaward.exception;

public class NotCorrectDataException extends RuntimeException{
    public NotCorrectDataException() {
    }

    public NotCorrectDataException(String message) {
        super(message);
    }
}
