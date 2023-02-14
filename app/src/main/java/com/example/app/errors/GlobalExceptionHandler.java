package com.example.app.errors;

import com.example.app.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorMessage handleResponseStatusException(
            ResponseStatusException   ex,
            WebRequest              request
    ) {

        return ErrorMessage.builder()
                .statusCode(HttpStatus.CONFLICT.value())
                .timeStamp(new Date())
                .message(ex.getReason())
                .description(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage handleUsernameNotFoundException(
            UsernameNotFoundException   ex,
            WebRequest                  request
    ) {

        return ErrorMessage.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timeStamp(new Date())
                .message(ex.getMessage())
                .description(request.getDescription(false))
                .build();
    }
}
