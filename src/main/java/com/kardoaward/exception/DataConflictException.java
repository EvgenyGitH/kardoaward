package com.kardoaward.exception;

public class DataConflictException extends RuntimeException{
    public DataConflictException() {
    }

    public DataConflictException(String message) {
        super(message);
    }
}
