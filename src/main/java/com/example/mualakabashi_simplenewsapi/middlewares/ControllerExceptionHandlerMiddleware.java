package com.example.mualakabashi_simplenewsapi.middlewares;


import com.example.mualakabashi_simplenewsapi.exceptions.NotActiveException;
import com.example.mualakabashi_simplenewsapi.exceptions.NotFoundException;
import com.example.mualakabashi_simplenewsapi.models.ResponseModel;
import org.quartz.SchedulerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllerExceptionHandlerMiddleware {
    @ExceptionHandler({NotFoundException.class, NotActiveException.class})
    public static ResponseEntity<ResponseModel<Object>> getErrorMessageForNotFound(RuntimeException e) {
        Throwable cause;
        Throwable result = e;
        while (null != (cause = result.getCause()) && (result != cause))
            result = cause;
        return ResponseModel.onError(result.getMessage(),404);
    }

    @ExceptionHandler({SchedulerException.class})
    public static ResponseEntity<ResponseModel<Object>> getErrorMessageForSchedulerException(RuntimeException e) {
        Throwable cause;
        Throwable result = e;
        while (null != (cause = result.getCause()) && (result != cause))
            result = cause;
        return ResponseModel.onError(result.getMessage(),501);
    }

    @ExceptionHandler(Exception.class)
    public static ResponseEntity<ResponseModel<Object>> getErrorMessageForBoolean(Exception e) {
        Throwable cause;
        Throwable result = e;
        while (null != (cause = result.getCause()) && (result != cause))
            result = cause;
        if (result.getMessage().equals("Bad credentials")) {
            return ResponseModel.onError(result.getMessage(),401);
        }
        return ResponseModel.onError(result.getMessage(),500);
    }
}
