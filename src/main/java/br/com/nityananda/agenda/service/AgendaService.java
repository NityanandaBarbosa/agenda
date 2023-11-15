package br.com.nityananda.agenda.service;

import br.com.nityananda.agenda.dtos.AgendaRecordDto;
import br.com.nityananda.agenda.models.Agenda;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AgendaService {
    Agenda registerAgenda(AgendaRecordDto agendaRecordDto);
    List<Agenda> getAll();
}
