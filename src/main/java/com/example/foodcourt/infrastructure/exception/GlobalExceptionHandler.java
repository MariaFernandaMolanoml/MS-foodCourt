package com.example.foodcourt.infrastructure.exception;

import com.example.foodcourt.domain.exception.DishCategoryNotFoundException;
import com.example.foodcourt.domain.exception.DishDescriptionNotFoundException;
import com.example.foodcourt.domain.exception.DishNameNotFoundException;
import com.example.foodcourt.domain.exception.DishPriceNotValidException;
import com.example.foodcourt.domain.exception.DishRestaurantNotFoundException;
import com.example.foodcourt.domain.exception.DuplicateNitException;
import com.example.foodcourt.domain.exception.InvalidNitException;
import com.example.foodcourt.domain.exception.InvalidPhoneException;
import com.example.foodcourt.domain.exception.MissingFieldsException;
import com.example.foodcourt.domain.exception.OwnerNotAllowedException;
import com.example.foodcourt.domain.exception.OwnerNotFoundException;
import com.example.foodcourt.domain.exception.RestaurantAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ApiResponse> buildResponse(HttpStatus status, Object message) {
        ApiResponse response = new ApiResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message
        );
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return buildResponse(HttpStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler({
            DishNameNotFoundException.class,
            DishDescriptionNotFoundException.class,
            DishPriceNotValidException.class,
            DishRestaurantNotFoundException.class,
            DishCategoryNotFoundException.class
    })
    public ResponseEntity<ApiResponse> handleDishValidation(RuntimeException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(RestaurantAlreadyExistsException.class)
    public ResponseEntity<ApiResponse> handleRestaurantAlreadyExists(RestaurantAlreadyExistsException ex) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler({InvalidNitException.class, InvalidPhoneException.class, OwnerNotAllowedException.class, MissingFieldsException.class})
    public ResponseEntity<ApiResponse> handleBadRequest(RuntimeException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(OwnerNotFoundException.class)
    public ResponseEntity<ApiResponse> handleOwnerNotFound(OwnerNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGeneric(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }
    @ExceptionHandler(DuplicateNitException.class)
    public ResponseEntity<ApiResponse> handleDuplicateNit(DuplicateNitException ex) {
        ApiResponse response = new ApiResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}

