package br.com.nityananda.agenda.service.impl;

import br.com.nityananda.agenda.dtos.AssociateRecordDto;
import br.com.nityananda.agenda.exceptions.CpfAlreadyUsed;
import br.com.nityananda.agenda.models.Associate;
import br.com.nityananda.agenda.repositories.AssociateRepository;
import br.com.nityananda.agenda.service.AssociateService;
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
    public Associate registerAssociate(AssociateRecordDto associateRecordDto){
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
