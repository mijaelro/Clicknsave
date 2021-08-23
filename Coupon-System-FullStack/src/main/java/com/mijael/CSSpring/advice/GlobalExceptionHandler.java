package com.mijael.CSSpring.advice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mijael.CSSpring.exceptions.IllegalActionException;
import com.mijael.CSSpring.exceptions.LoginException;
import com.mijael.CSSpring.exceptions.PurchaseException;
import com.mijael.CSSpring.exceptions.SaveException;
import com.mijael.CSSpring.exceptions.TokenErrorException;

@ControllerAdvice
@RestController
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalActionException.class, PurchaseException.class,
            SaveException.class, Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handler1(Exception e) {
        return new ErrorDetails("Bad request", e.getMessage());
    }

    @ExceptionHandler({LoginException.class, SecurityException.class, TokenErrorException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDetails handler2(Exception e) {
        return new ErrorDetails("Unauthorized", e.getMessage());
    }
}
