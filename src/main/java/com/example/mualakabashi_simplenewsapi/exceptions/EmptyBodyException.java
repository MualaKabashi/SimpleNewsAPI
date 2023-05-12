package com.example.mualakabashi_simplenewsapi.exceptions;

public class EmptyBodyException extends RuntimeException {
    public EmptyBodyException() {
    }

    public EmptyBodyException(String message) {
        super(message);
    }
}
