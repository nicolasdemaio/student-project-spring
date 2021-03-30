package com.ndemaio.studentprojectspring.config;

import com.ndemaio.studentprojectspring.config.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

//Esta clase se encarga de handlear excepciones desde RestController hasta Repository
@ControllerAdvice (annotations = RestController.class)
public class ExceptionConfig {

    @ExceptionHandler ({EmailNotAvailableException.class, NotAvailablePlacesException.class, StudentIsAlreadyEnrolled.class, StudentUnderAgeException.class})
    public ResponseEntity<Object> badRequestException(Exception exception){

        // Creamos un payload, un objeto con los datos de la excepcion y el status
        ApiException apiException = new ApiException(
                exception.getMessage(),
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST
        );

        // Se retorna response entity, con body de la api y status. Jackson deberia transformarlo en JSON el body.
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiException);
    }

    @ExceptionHandler (EntityNotFoundException.class)
    public ResponseEntity<Object> entityNotFoundException(Exception exception){

        ApiException apiException = new ApiException(
                exception.getMessage(),
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiException);
    }

}
