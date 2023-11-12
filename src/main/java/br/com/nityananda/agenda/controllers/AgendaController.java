package br.com.nityananda.agenda.controllers;

import br.com.nityananda.agenda.dtos.AgendaRecordDto;
import br.com.nityananda.agenda.dtos.AssociateRecordDto;
import br.com.nityananda.agenda.models.Agenda;
import br.com.nityananda.agenda.models.Associate;
import br.com.nityananda.agenda.service.AgendaService;
import br.com.nityananda.agenda.service.AssociateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/agenda")
@Tag(name= "Agenda")
public class AgendaController {
    final private AgendaService service;

    public AgendaController(AgendaService service) {
        this.service = service;
    }

    @PostMapping()
    @Operation(summary = "Register a new agenda")
    public ResponseEntity<Agenda> registerAssociate(@RequestBody @Valid AgendaRecordDto agendaRecordDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerAgenda(agendaRecordDto));
    }

    @DeleteMapping(value = "{id}")
    @Operation(summary = "Delete a agenda")
    public ResponseEntity<Void> deleteAgenda(@PathVariable String id){
        service.deleteAgenda(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "/all")
    @Operation(summary = "Get all agendas")
    public ResponseEntity<List<Agenda>> getAll(){
        ;
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }
}
