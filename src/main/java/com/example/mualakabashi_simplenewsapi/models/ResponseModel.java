package com.example.mualakabashi_simplenewsapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
public class ResponseModel<T> {
    private T data;
    private String error;

    public static <T> ResponseEntity<ResponseModel<T>> onSuccess(T data,int statusCode) {
        return ResponseEntity.status(statusCode).body(new ResponseModel<>(data, null));
    }

    public static <T> ResponseEntity<ResponseModel<T>> onError(String error,int statusCode) {
        return ResponseEntity.status(statusCode).body(new ResponseModel<>(null, error));
    }

}