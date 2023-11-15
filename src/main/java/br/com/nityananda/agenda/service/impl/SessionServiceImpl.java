package br.com.nityananda.agenda.service.impl;

import br.com.nityananda.agenda.dtos.SessionRecordDto;
import br.com.nityananda.agenda.dtos.SessionResponseDto;
import br.com.nityananda.agenda.exceptions.AgendaAlreadyUsedInSession;
import br.com.nityananda.agenda.exceptions.AgendaNotFound;
import br.com.nityananda.agenda.models.Session;
import br.com.nityananda.agenda.repositories.AgendaRepository;
import br.com.nityananda.agenda.repositories.SessionRepository;
import br.com.nityananda.agenda.service.SessionService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SessionServiceImpl implements SessionService {
    final SessionRepository repository;

    final AgendaRepository agendaRepository;

    public SessionServiceImpl(SessionRepository repository, AgendaRepository agendaRepository, AgendaRepository agendaRepository1) {
        this.repository = repository;
        this.agendaRepository = agendaRepository1;
    }

    final
    @Override
    public SessionResponseDto registerSession(SessionRecordDto sessionRecordDto) {
        try{
            var agenda = agendaRepository.findById(UUID.fromString(sessionRecordDto.agenda_id()));
            if(agenda.isEmpty()){
                throw new AgendaNotFound("Agenda not found");
            }
            var _agenda = agenda.get();

            var session = new Session();
            session.setAgenda(_agenda);
            var endSession = sessionRecordDto.end_session();
            session.setEndSession(endSession != null ? endSession : session.getBeginSession().plusMinutes(1));

            var savedSession = repository.save(session);
            return savedSession.toGetDto();
        }catch (DataIntegrityViolationException e){
            throw new AgendaAlreadyUsedInSession("Agenda already used in session");
        }
    }

    @Override
    public List<SessionResponseDto> getAll() {
        var sessions = repository.findAll();
        return sessions.stream().map(session -> {;
            return session.toGetDto();
        }).toList();
    }
}
