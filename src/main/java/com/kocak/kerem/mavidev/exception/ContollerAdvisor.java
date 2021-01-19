package com.kocak.kerem.mavidev.exception;

import com.kocak.kerem.mavidev.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ContollerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoUserFoundException.class)
    public ResponseEntity<Object> handleNoUserFoundException(Exception ex) {
        return new ResponseEntity<>(Constants.NO_USER_FOUND_EXCEPTION, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotActiveException.class)
    public ResponseEntity<Object> handleUserNotActiveException(Exception ex) {
        return new ResponseEntity<>(Constants.USER_NOT_ACTIVE, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        log.error("Unknown Exception: {}", ex.getLocalizedMessage());
        log.error("Unknown Exception: {}", (Object) ex.getStackTrace());
        return new ResponseEntity<>(Constants.MASTER_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}