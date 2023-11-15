package br.com.nityananda.agenda.services;

import br.com.nityananda.agenda.dtos.AgendaRecordDto;
import br.com.nityananda.agenda.dtos.AssociateRecordDto;
import br.com.nityananda.agenda.exceptions.CpfAlreadyUsed;
import br.com.nityananda.agenda.exceptions.CpfInvalid;
import br.com.nityananda.agenda.models.Agenda;
import br.com.nityananda.agenda.models.Associate;
import br.com.nityananda.agenda.repositories.AgendaRepository;
import br.com.nityananda.agenda.service.impl.AgendaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class AgendaServiceImplTest {
    @InjectMocks
    private AgendaServiceImpl agendaService;

    @Mock
    private AgendaRepository agendaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    final Date birthdate = new Date(631152000000L);

    @Test
    void registerAgenda_Success() {
        AgendaRecordDto agendaRecordDto = new AgendaRecordDto("Agenda1", "New Agenda");

        Agenda expectedAgenda = new Agenda();
        BeanUtils.copyProperties(agendaRecordDto, expectedAgenda);

        Mockito.when(agendaRepository.save(any(Agenda.class))).thenReturn(expectedAgenda);

        Agenda actualAgenda = agendaService.registerAgenda(agendaRecordDto);

        assertNotNull(actualAgenda);
        assertEquals(expectedAgenda, actualAgenda);
    }


    @Test
    void getAllAgenda_Success() {
        Agenda agenda1 = new Agenda();
        agenda1.setTitle("Agenda 1");

        Agenda agenda2 = new Agenda();
        agenda2.setTitle("Agenda 2");

        List<Agenda> expectedAgendas = Arrays.asList(agenda1, agenda2);

        Mockito.when(agendaRepository.findAll()).thenReturn(expectedAgendas);

        List<Agenda> actualAgenda = agendaRepository.findAll();

        assertEquals(expectedAgendas, actualAgenda);
    }
}
