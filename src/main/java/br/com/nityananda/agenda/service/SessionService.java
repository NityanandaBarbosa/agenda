package br.com.nityananda.agenda.service;

import br.com.nityananda.agenda.dtos.AgendaRecordDto;
import br.com.nityananda.agenda.dtos.SessionRecordDto;
import br.com.nityananda.agenda.models.Agenda;
import br.com.nityananda.agenda.models.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SessionService {
    Session registerSession(SessionRecordDto sessionRecordDto);
    void deleteSession(String uuid);
    List<Session> getAll();
}
