package br.com.nityananda.agenda.core.controllers.handler;

import br.com.nityananda.agenda.associate.exceptions.CpfAlreadyUsed;
import br.com.nityananda.agenda.associate.exceptions.CpfInvalid;
import br.com.nityananda.agenda.core.controllers.exception.HttpError;
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
        HttpError err = new HttpError(HttpStatus.NO_CONTENT.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(err);
    }
}
