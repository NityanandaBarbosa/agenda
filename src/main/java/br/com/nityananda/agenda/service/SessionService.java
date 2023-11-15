package br.com.nityananda.agenda.service;

import br.com.nityananda.agenda.dtos.SessionRecordDto;
import br.com.nityananda.agenda.dtos.SessionResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SessionService {
    SessionResponseDto registerSession(SessionRecordDto sessionRecordDto);
    List<SessionResponseDto> getAll();
}
