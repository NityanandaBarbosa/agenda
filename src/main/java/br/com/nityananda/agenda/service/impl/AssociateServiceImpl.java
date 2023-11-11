package br.com.nityananda.agenda.service.impl;

import br.com.nityananda.agenda.dtos.AssociateRecordDto;
import br.com.nityananda.agenda.exceptions.CpfAlreadyUsed;
import br.com.nityananda.agenda.models.Associate;
import br.com.nityananda.agenda.repositories.AssociateRepository;
import br.com.nityananda.agenda.service.AssociateService;
import br.com.nityananda.agenda.service.ValidateCpfService;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociateServiceImpl implements AssociateService {
    final AssociateRepository repository;

    final ValidateCpfService validateCpfService;

    public AssociateServiceImpl(AssociateRepository repository, ValidateCpfService validateCpfService) {
        this.repository = repository;
        this.validateCpfService = validateCpfService;
    }

    @Override
    public Associate registerAssociate(AssociateRecordDto associateRecordDto){
        try{
            var user = new Associate();
            BeanUtils.copyProperties(associateRecordDto, user);
            user.setCpf(associateRecordDto.cpf().replaceAll("[^\\d]", ""));
            validateCpfService.validate(user.getCpf());
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
