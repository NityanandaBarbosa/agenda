package br.com.nityananda.agenda.handler;

import br.com.nityananda.agenda.exceptions.*;
import br.com.nityananda.agenda.handler.exception.HttpError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(CpfAlreadyUsed.class)
    public ResponseEntity<HttpError> validation(CpfAlreadyUsed e, HttpServletRequest request){
        HttpError err = new HttpError(HttpStatus.CONFLICT.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler(CpfInvalid.class)
    public ResponseEntity<HttpError> validation(CpfInvalid e, HttpServletRequest request){
        HttpError err = new HttpError(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err);
    }

    @ExceptionHandler(AgendaAlreadyUsedInSession.class)
    public ResponseEntity<HttpError> validation(AgendaAlreadyUsedInSession e, HttpServletRequest request){
        HttpError err = new HttpError(HttpStatus.CONFLICT.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler(AgendaNotFound.class)
    public ResponseEntity<HttpError> validation(AgendaNotFound e, HttpServletRequest request){
        HttpError err = new HttpError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<HttpError> validation(IllegalArgumentException e, HttpServletRequest request){
        HttpError err = new HttpError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(AlreadyVoted.class)
    public ResponseEntity<HttpError> validation(AlreadyVoted e, HttpServletRequest request){
        HttpError err = new HttpError(HttpStatus.CONFLICT.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler(AssociateNotFound.class)
    public ResponseEntity<HttpError> validation(AssociateNotFound e, HttpServletRequest request){
        HttpError err = new HttpError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(SessionNotFound.class)
    public ResponseEntity<HttpError> validation(SessionNotFound e, HttpServletRequest request){
        HttpError err = new HttpError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(SessionExpired.class)
    public ResponseEntity<HttpError> validation(SessionExpired e, HttpServletRequest request){
        HttpError err = new HttpError(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err);
    }

}
