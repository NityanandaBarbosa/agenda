package br.com.nityananda.agenda.controllers;

import br.com.nityananda.agenda.dtos.AssociateRecordDto;
import br.com.nityananda.agenda.exceptions.CpfAlreadyUsed;
import br.com.nityananda.agenda.models.Associate;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AssociateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssociateService associateService;

    @Test
    void registerAssociate() throws Exception {
        final Date birthdate = new Date(631152000000L);
        AssociateRecordDto associateRecordDto = new AssociateRecordDto("Associate Name", "12345678901", birthdate);

        String jsonRequest = "{\"name\":\"Nityananda Barbosa\", \"cpf\":\"12345678901\", \"birthdate\":\"1990-01-01\"}";

        Associate associate = new Associate();
        BeanUtils.copyProperties(associateRecordDto, associate);
        associate.setRegistration(UUID.randomUUID());

        Mockito.when(associateService.registerAssociate(Mockito.any(AssociateRecordDto.class)))
                .thenReturn(associate);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/associate")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Nityananda Barbosa"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("12345678901"));
    }

    @Test
    void registerAssociateThrowCpfAlreadyInUse() throws Exception {
        final Date birthdate = new Date(631152000000L);
        AssociateRecordDto associateRecordDto = new AssociateRecordDto("Associate Name", "12345678901", birthdate);

        String jsonRequest = "{\"name\":\"Nityananda Barbosa\", \"cpf\":\"12345678901\", \"birthdate\":\"1990-01-01\"}";

        Associate associate = new Associate();
        BeanUtils.copyProperties(associateRecordDto, associate);
        associate.setRegistration(UUID.randomUUID());

        Mockito.when(associateService.registerAssociate(Mockito.any(AssociateRecordDto.class)))
                .thenThrow(CpfAlreadyUsed.class);

        mockMvc.perform(MockMvcRequestBuilders
            .post("/associate")
            .content(jsonRequest)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isConflict());
    }

    @Test
    void getAllAssociates() throws Exception {
        Associate associate1 = new Associate();
        associate1.setRegistration(UUID.randomUUID());
        associate1.setName("Associate 1");
        associate1.setCpf("12345678901");
        associate1.setBirthdate(new Date());

        Associate associate2 = new Associate();
        associate2.setRegistration(UUID.randomUUID());
        associate2.setName("Associate 2");
        associate2.setCpf("98765432101");
        associate2.setBirthdate(new Date());

        Mockito.when(associateService.getAll()).thenReturn(Arrays.asList(associate1, associate2));

        mockMvc.perform(MockMvcRequestBuilders
            .get("/associate/all")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2)) // Verifica se há dois elementos no array
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Associate 1")) // Verifica o nome do primeiro associado
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Associate 2")); // Verifica o nome do segundo associado
    }
}
