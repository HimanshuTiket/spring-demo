package com.himanshu.errorHandler;

import com.himanshu.error.BadRequestError;
import com.himanshu.error.InternalServerError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> invalidRequestHandler(MethodArgumentNotValidException err){
        Map<String, String> errMap = new HashMap<>();

        err.getBindingResult().getFieldErrors().forEach(error -> {
            errMap.put(error.getField(), error.getDefaultMessage());
        });

        BadRequestError error = new BadRequestError(errMap);

        return error.getError();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestError.class)
    public Map<String, String> badRequestError(BadRequestError err){
        return err.getError();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerError.class)
    public Map<String, String> internalServerError(InternalServerError err){
        return err.getError();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, String> unexpectedError(Exception err){
        InternalServerError error = new InternalServerError(err.getMessage());

        return error.getError();
    }
}
