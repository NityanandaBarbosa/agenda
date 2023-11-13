package br.com.nityananda.agenda.dtos;

import br.com.nityananda.agenda.enums.SessionStatus;
import br.com.nityananda.agenda.models.Agenda;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class  SessionResponseDto {
    final String id;
    final Agenda agenda;
    final LocalDateTime begin_session;
    final LocalDateTime end_session;
    final SessionStatus session_status;

    public SessionResponseDto(String id, Agenda agenda, LocalDateTime beginSession, LocalDateTime endSession) {
        this.id = id;
        this.agenda = agenda;
        this.begin_session = beginSession;
        this.end_session = endSession;
        this.session_status = calculateSessionStatus();
    }

    public SessionStatus calculateSessionStatus() {
        LocalDateTime now = LocalDateTime.now();

        if (now.isBefore(end_session)) {
            return SessionStatus.OPEN;
        } else {
            return SessionStatus.FINISHED;
        }
    }

    public SessionStatus getStatus(){
        return session_status;
    }
}
