package br.com.nityananda.agenda.service;

import org.springframework.stereotype.Service;

@Service
public interface ValidateCpfService {
    boolean validate(String cpf);
}
