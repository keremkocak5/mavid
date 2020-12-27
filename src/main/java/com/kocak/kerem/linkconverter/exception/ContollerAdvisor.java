package com.kocak.kerem.linkconverter.exception;

import com.kocak.kerem.linkconverter.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ContollerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        log.error("Unknown Exception: " + ex.getLocalizedMessage());
        log.error("Unknown Exception: " + ex.getStackTrace());
        return new ResponseEntity<>(Constants.MASTER_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}