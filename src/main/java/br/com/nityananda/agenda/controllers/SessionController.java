package br.com.nityananda.agenda.controllers;

import br.com.nityananda.agenda.dtos.SessionRecordDto;
import br.com.nityananda.agenda.dtos.SessionResponseDto;
import br.com.nityananda.agenda.service.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController()
@RequestMapping("/session")
@Tag(name= "Session")
public class SessionController {
    final private SessionService service;

    public SessionController(SessionService service) {
        this.service = service;
    }

    @PostMapping()
    @Operation(summary = "Register a new session")
    public ResponseEntity<SessionResponseDto> registerAssociate(@RequestBody @Valid SessionRecordDto sessionRecordDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerSession(sessionRecordDto));
    }

    @GetMapping(value = "/all")
    @Operation(summary = "Get all sessions")
    public ResponseEntity<List<SessionResponseDto>> getAll(){
        ;
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }
}
