package br.com.nityananda.agenda.associate.controllers;

import br.com.nityananda.agenda.associate.dtos.AssociateRecordDto;
import br.com.nityananda.agenda.associate.models.Associate;
import br.com.nityananda.agenda.associate.repositories.AssociateRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/associate")
@Tag(name= "Associate")
public class AssociateController {
    final private AssociateRepository repository;

    public AssociateController(AssociateRepository repository) {
        this.repository = repository;
    }

    @PostMapping()
    @Operation(summary = "Register a new associate")
    public ResponseEntity<Associate> registerAssociate(@RequestBody @Valid AssociateRecordDto associateRecordDto){
        var user = new Associate();
        BeanUtils.copyProperties(associateRecordDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(user));
    }

    @GetMapping(value = "/all")
    @Operation(summary = "Retrieve all associates")
    public ResponseEntity<List<Associate>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }
}
