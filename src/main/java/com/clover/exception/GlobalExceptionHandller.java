package com.clover.exception;

import com.clover.helper.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandller {

    Logger log = LoggerFactory.getLogger(GlobalExceptionHandller.class);


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
        log.error("handleResourceNotFoundException() started");
        String msg = ex.getMessage();
        ApiResponse api = new ApiResponse(msg,false);
        log.error("handleResourceNotFoundException() completed");
        return new ResponseEntity<>(api, HttpStatus.NOT_FOUND);
    }


    /**
     * this exception handler method used for validation error
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String , String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        log.error("handleMethodArgumentNotValidException() started");
        Map<String , String> map = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->
        {
            String field = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            map.put(field , message);
        });
        log.error("handleMethodArgumentNotValidException() completed");
        return new ResponseEntity<Map<String , String>>(map,HttpStatus.BAD_REQUEST);
    }

}

