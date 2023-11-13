package br.com.nityananda.agenda.controllers;

import br.com.nityananda.agenda.dtos.SessionGetDto;
import br.com.nityananda.agenda.dtos.SessionRecordDto;
import br.com.nityananda.agenda.dtos.VoteRecordDto;
import br.com.nityananda.agenda.models.Vote;
import br.com.nityananda.agenda.service.SessionService;
import br.com.nityananda.agenda.service.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/vote")
@Tag(name= "Vote")
public class VoteController {
    final private VoteService service;

    public VoteController(VoteService service) {
        this.service = service;
    }

    @PostMapping()
    @Operation(summary = "Register a new vote")
    public ResponseEntity<Vote> submitVote(@RequestBody @Valid VoteRecordDto VoteRecordDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.submitVote(VoteRecordDto));
    }

//    @DeleteMapping(value = "{id}")
//    @Operation(summary = "Delete a session")
//    public ResponseEntity<Void> deleteAgenda(@PathVariable String id){
//        service.deleteSession(id);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
//
//    @GetMapping(value = "/all")
//    @Operation(summary = "Get all sessions")
//    public ResponseEntity<List<SessionGetDto>> getAll(){
//        ;
//        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
//    }
}
