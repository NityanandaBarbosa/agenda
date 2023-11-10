package br.com.nityananda.agenda.associate.service;

import br.com.nityananda.agenda.associate.dtos.AssociateRecordDto;
import br.com.nityananda.agenda.associate.exceptions.CpfAlreadyUsed;
import br.com.nityananda.agenda.associate.exceptions.CpfInvalid;
import br.com.nityananda.agenda.associate.models.Associate;

import java.util.List;

public interface AssociateService {
    Associate registeAssociate(AssociateRecordDto associateRecordDto) throws CpfAlreadyUsed, CpfInvalid;
    List<Associate> getAll();
}
