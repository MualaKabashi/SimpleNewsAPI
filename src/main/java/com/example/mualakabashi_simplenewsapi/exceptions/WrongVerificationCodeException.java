package com.example.mualakabashi_simplenewsapi.exceptions;

public class WrongVerificationCodeException extends RuntimeException {
    public WrongVerificationCodeException() {
    }

    public WrongVerificationCodeException(String message) {
        super(message);
    }
}
