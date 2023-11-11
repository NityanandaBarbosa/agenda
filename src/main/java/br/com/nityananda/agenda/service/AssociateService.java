package br.com.nityananda.agenda.service;

import br.com.nityananda.agenda.dtos.AssociateRecordDto;
import br.com.nityananda.agenda.exceptions.CpfAlreadyUsed;
import br.com.nityananda.agenda.exceptions.CpfInvalid;
import br.com.nityananda.agenda.models.Associate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AssociateService {
    Associate registerAssociate(AssociateRecordDto associateRecordDto);
    List<Associate> getAll();
}
