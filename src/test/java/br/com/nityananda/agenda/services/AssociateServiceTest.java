package br.com.nityananda.agenda.services;

import br.com.nityananda.agenda.dtos.AssociateRecordDto;
import br.com.nityananda.agenda.exceptions.CpfAlreadyUsed;
import br.com.nityananda.agenda.exceptions.CpfInvalid;
import br.com.nityananda.agenda.models.Associate;
import br.com.nityananda.agenda.repositories.AssociateRepository;
import br.com.nityananda.agenda.service.ValidateCpfService;
import br.com.nityananda.agenda.service.impl.AssociateServiceImpl;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class AssociateServiceImplTest {

    @InjectMocks
    private AssociateServiceImpl associateService;

    @Mock
    private AssociateRepository associateRepository;

    @Mock
    private ValidateCpfService validateCpfService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    final Date birthdate = new Date(631152000000L);

    @Test
    void registerAssociate_Success() {
        AssociateRecordDto associateRecordDto = new AssociateRecordDto("Associate Name", "12345678901", birthdate);

        Associate expectedAssociate = new Associate();
        BeanUtils.copyProperties(associateRecordDto, expectedAssociate);

        Mockito.when(associateRepository.save(any(Associate.class))).thenReturn(expectedAssociate);

        Associate actualAssociate = associateService.registerAssociate(associateRecordDto);

        assertNotNull(actualAssociate);
        assertEquals(expectedAssociate, actualAssociate);
    }

    @Test
    void registerAssociate_CpfAlreadyUsed() {
        AssociateRecordDto associateRecordDto = new AssociateRecordDto("Associate Name", "12345678901", birthdate);

        Mockito.when(validateCpfService.validate(any(String.class))).thenThrow(new CpfInvalid("Cpf Invalid"));

        assertThrows(CpfInvalid.class, () -> associateService.registerAssociate(associateRecordDto));
    }

    @Test
    void registerAssociate_CpfInvalid() {
        AssociateRecordDto associateRecordDto = new AssociateRecordDto("Associate Name", "12345678901", birthdate);

        Mockito.when(associateRepository.save(any(Associate.class))).thenThrow(new DataIntegrityViolationException(""));

        assertThrows(CpfAlreadyUsed.class, () -> associateService.registerAssociate(associateRecordDto));
    }

    @Test
    void getAllAssociates() {
        Associate associate1 = new Associate();
        associate1.setName("Associate 1");

        Associate associate2 = new Associate();
        associate2.setName("Associate 2");

        List<Associate> expectedAssociates = Arrays.asList(associate1, associate2);

        Mockito.when(associateRepository.findAll()).thenReturn(expectedAssociates);

        List<Associate> actualAssociates = associateService.getAll();

        assertEquals(expectedAssociates, actualAssociates);
    }
}