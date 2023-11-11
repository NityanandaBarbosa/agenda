package br.com.nityananda.agenda.controllers;

import br.com.nityananda.agenda.dtos.AssociateRecordDto;
import br.com.nityananda.agenda.models.Associate;
import br.com.nityananda.agenda.service.AssociateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/associate")
@Tag(name= "Associate")
public class AssociateController {
    final private AssociateService service;

    public AssociateController(AssociateService service) {
        this.service = service;
    }

    @PostMapping()
    @Operation(summary = "Register a new associate")
    public ResponseEntity<Associate> registerAssociate(@RequestBody @Valid AssociateRecordDto associateRecordDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerAssociate(associateRecordDto));
    }

    @GetMapping(value = "/all")
    @Operation(summary = "Retrieve all associates")
    public ResponseEntity<List<Associate>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }
}
