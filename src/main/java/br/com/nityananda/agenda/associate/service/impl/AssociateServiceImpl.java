package br.com.nityananda.agenda.associate.service.impl;

import br.com.nityananda.agenda.associate.dtos.AssociateRecordDto;
import br.com.nityananda.agenda.associate.exceptions.CpfAlreadyUsed;
import br.com.nityananda.agenda.associate.models.Associate;
import br.com.nityananda.agenda.associate.repositories.AssociateRepository;
import br.com.nityananda.agenda.associate.service.AssociateService;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociateServiceImpl implements AssociateService {
    final AssociateRepository repository;

    public AssociateServiceImpl(AssociateRepository repository) {
        this.repository = repository;
    }

    @Override
    public Associate registeAssociate(AssociateRecordDto associateRecordDto){
        try{
            var user = new Associate();
            BeanUtils.copyProperties(associateRecordDto, user);
            return repository.save(user);
        }catch (DataIntegrityViolationException e){
            throw new CpfAlreadyUsed("Cpf already in use");
        }
    }

    @Override
    public List<Associate> getAll() {
        return repository.findAll();
    }
}
