package br.com.kactus.crud.Exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class HandlerException {


    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> notFoundException(NotFoundException ex,
                                                                       WebRequest request) {
        ReturnError returnError = new ReturnError();
        returnError.setCode(HttpStatus.NOT_FOUND);
        returnError.setMessage(ex.getMessage());
        return new ResponseEntity<>(returnError, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        ReturnError erroRepresentation = new ReturnError();
        erroRepresentation.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
        erroRepresentation.setMessage(ex.getMessage());
        return new ResponseEntity<>(erroRepresentation, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}