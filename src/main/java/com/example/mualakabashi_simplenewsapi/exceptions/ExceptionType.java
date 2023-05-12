package com.example.mualakabashi_simplenewsapi.exceptions;


public enum ExceptionType {

    ENTITY_NOT_FOUND("Not found"),
    DUPLICATE_ENTITY("Duplicate"),
    BAD_REQUEST("Bad Request"),
    EMPTY_DATA_SET("Empty"),
    USER_DOES_NOT_EXIST("User does not exists , please register first!"),
    USER_ALREADY_EXISTS("User already exists"),
    EMPTY_EMAIL_FIELD("Email should not be null"),
    WRONG_CREDENTIALS("Wrong verification code exception"),
    NEWS_NOT_FOUND("News does not exist!"),
    INVALID_SCHEDULED_DATE("Scheduled date must be after current time!");
    ;

    private String value;

    private ExceptionType(String value) {

        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
