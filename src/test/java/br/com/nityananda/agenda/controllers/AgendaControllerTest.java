package br.com.nityananda.agenda.controllers;

import br.com.nityananda.agenda.dtos.AgendaRecordDto;
import br.com.nityananda.agenda.dtos.AssociateRecordDto;
import br.com.nityananda.agenda.exceptions.CpfAlreadyUsed;
import br.com.nityananda.agenda.exceptions.CpfInvalid;
import br.com.nityananda.agenda.models.Agenda;
import br.com.nityananda.agenda.models.Associate;
import br.com.nityananda.agenda.service.AgendaService;
import br.com.nityananda.agenda.service.AssociateService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AgendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AgendaService agendaService;

    @Test
    void registerAgenda() throws Exception {
        AgendaRecordDto agendaRecordDto = new AgendaRecordDto("Teste1", "Teste2");

        String jsonRequest = "{\"title\":\"Teste1\", \"description\":\"Teste2\"}";

        Agenda agenda = new Agenda();
        BeanUtils.copyProperties(agendaRecordDto, agenda);
        agenda.setId(UUID.randomUUID());

        Mockito.when(agendaService.registerAgenda(Mockito.any(AgendaRecordDto.class)))
                .thenReturn(agenda);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/agenda")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(agenda.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(agenda.getDescription()));
    }

    @Test
    void getAllAgendas() throws Exception {
        Agenda agenda1 = new Agenda();
        agenda1.setTitle("Title1");
        agenda1.setId(UUID.randomUUID());


        Agenda agenda2 = new Agenda();
        agenda2.setTitle("Title2");
        agenda2.setId(UUID.randomUUID());

        Mockito.when(agendaService.getAll()).thenReturn(Arrays.asList(agenda1, agenda2));

        mockMvc.perform(MockMvcRequestBuilders
            .get("/agenda/all")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value(agenda1.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value(agenda2.getTitle()));
    }
}
