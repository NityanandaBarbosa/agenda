package br.com.nityananda.agenda.service.impl;

import br.com.nityananda.agenda.dtos.VoteRecordDto;
import br.com.nityananda.agenda.dtos.VotingCountingResponseDto;
import br.com.nityananda.agenda.enums.SessionStatus;
import br.com.nityananda.agenda.enums.VoteOptions;
import br.com.nityananda.agenda.exceptions.*;
import br.com.nityananda.agenda.models.Vote;
import br.com.nityananda.agenda.repositories.AssociateRepository;
import br.com.nityananda.agenda.repositories.SessionRepository;
import br.com.nityananda.agenda.repositories.VoteRepository;
import br.com.nityananda.agenda.service.VoteService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VoteServiceImpl implements VoteService {
    final VoteRepository repository;
    final AssociateRepository associateRepository;

    final SessionRepository sessionRepository;


    public VoteServiceImpl(VoteRepository repository, AssociateRepository associateRepository, SessionRepository sessionRepository) {
        this.repository = repository;
        this.associateRepository = associateRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Vote submitVote(VoteRecordDto voteRecordDto) {
        var associate = associateRepository.findByCpf(voteRecordDto.cpf());
        if(associate == null) throw new AssociateNotFound("Associate not found");

        var sessionOptional = sessionRepository.findById(UUID.fromString(voteRecordDto.session_id()));
        if(sessionOptional.isEmpty()) throw new SessionNotFound("Session not found");

        var _session = sessionOptional.get();
        var _sessionDto = _session.toGetDto();
        if(_sessionDto.getStatus() == SessionStatus.FINISHED) throw new SessionExpired("The session already finished");

       var voted = repository.findByAssociateAndSession(associate, _session);
       if(voted != null) throw new AlreadyVoted("Associate already voted to this session");

        var vote = new Vote();
        vote.setAssociate(associate);
        vote.setSession(_session);
        vote.setVote(voteRecordDto.vote_option());

        return repository.save(vote);
    }

    @Override
    public VotingCountingResponseDto voteCounting(String sessionId) {
        var sessionOptional = sessionRepository.findById(UUID.fromString(sessionId ));
        if(sessionOptional.isEmpty()) throw new SessionNotFound("Session not found");

        var _session = sessionOptional.get();
        var _sessionDto = _session.toGetDto();
        if(_sessionDto.getStatus() == SessionStatus.OPEN) throw new SessionNotExpired("Not allowed to counting, because the session is not finished yet");

        var votes = repository.findAllBySession(_session);
        var noVotes = votes.stream().map(vote -> vote.getVote() == VoteOptions.No).toList().size();
        var yesVotes = votes.stream().map(vote -> vote.getVote() == VoteOptions.Yes).toList().size();


        return new VotingCountingResponseDto(yesVotes, noVotes);
    }
}
