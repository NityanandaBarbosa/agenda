package br.com.nityananda.agenda.service;

import br.com.nityananda.agenda.dtos.VoteRecordDto;
import br.com.nityananda.agenda.dtos.VotingCountingResponseDto;
import br.com.nityananda.agenda.models.Vote;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoteService {

    List<Vote> getAll();
    Vote submitVote(VoteRecordDto voteRecordDto);
    VotingCountingResponseDto voteCounting(String sessionId);
}
