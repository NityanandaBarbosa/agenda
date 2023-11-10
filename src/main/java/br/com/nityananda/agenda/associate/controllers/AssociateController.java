package br.com.nityananda.agenda.associate.controllers;

import br.com.nityananda.agenda.associate.dtos.AssociateRecordDto;
import br.com.nityananda.agenda.associate.exceptions.CpfAlreadyUsed;
import br.com.nityananda.agenda.associate.exceptions.CpfInvalid;
import br.com.nityananda.agenda.associate.models.Associate;
import br.com.nityananda.agenda.associate.repositories.AssociateRepository;
import br.com.nityananda.agenda.associate.service.AssociateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Hashtable;
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
    public ResponseEntity<Object> registerAssociate(@RequestBody @Valid AssociateRecordDto associateRecordDto){
        var error = new HashMap<String, String>();
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.registeAssociate(associateRecordDto));
        }catch (CpfAlreadyUsed e){
            error.put("message: ", "Cpf already in use");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }catch (CpfInvalid e){
            error.put("message: ", "Cpf is invalid");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

    }

    @GetMapping(value = "/all")
    @Operation(summary = "Retrieve all associates")
    public ResponseEntity<List<Associate>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }
}
