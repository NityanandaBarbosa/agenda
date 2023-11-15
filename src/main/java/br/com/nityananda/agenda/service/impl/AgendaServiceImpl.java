package br.com.nityananda.agenda.service.impl;

import br.com.nityananda.agenda.dtos.AgendaRecordDto;
import br.com.nityananda.agenda.models.Agenda;
import br.com.nityananda.agenda.repositories.AgendaRepository;
import br.com.nityananda.agenda.service.AgendaService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AgendaServiceImpl implements AgendaService {
    final AgendaRepository repository;

    public AgendaServiceImpl(AgendaRepository repository) {
        this.repository = repository;
    }

    final
    @Override
    public Agenda registerAgenda(AgendaRecordDto agendaRecordDto) {
        var agenda = new Agenda();
        BeanUtils.copyProperties(agendaRecordDto, agenda);
        return repository.save(agenda);
    }

    @Override
    public List<Agenda> getAll() {
        return repository.findAll();
    }
}
