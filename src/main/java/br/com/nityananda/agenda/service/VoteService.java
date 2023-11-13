package br.com.nityananda.agenda.service;

import br.com.nityananda.agenda.dtos.VoteRecordDto;
import br.com.nityananda.agenda.models.Vote;
import org.springframework.stereotype.Service;

@Service
public interface VoteService {
    Vote submitVote(VoteRecordDto voteRecordDto);
}
