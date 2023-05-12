package com.example.mualakabashi_simplenewsapi.exceptions;

public class DuplicateRecordException extends RuntimeException {
    public DuplicateRecordException() {
    }

    public DuplicateRecordException(String message) {
        super(message);
    }
}
