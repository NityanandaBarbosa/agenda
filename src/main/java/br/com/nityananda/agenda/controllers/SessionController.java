package br.com.nityananda.agenda.controllers;

import br.com.nityananda.agenda.dtos.AgendaRecordDto;
import br.com.nityananda.agenda.dtos.SessionGetDto;
import br.com.nityananda.agenda.dtos.SessionRecordDto;
import br.com.nityananda.agenda.models.Agenda;
import br.com.nityananda.agenda.models.Session;
import br.com.nityananda.agenda.service.AgendaService;
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
    public ResponseEntity<SessionGetDto> registerAssociate(@RequestBody @Valid SessionRecordDto sessionRecordDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerSession(sessionRecordDto));
    }

    @DeleteMapping(value = "{id}")
    @Operation(summary = "Delete a session")
    public ResponseEntity<Void> deleteAgenda(@PathVariable String id){
        service.deleteSession(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "/all")
    @Operation(summary = "Get all sessions")
    public ResponseEntity<List<SessionGetDto>> getAll(){
        ;
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }
}
