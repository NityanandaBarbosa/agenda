package br.com.nityananda.agenda.service.impl;

import br.com.nityananda.agenda.exceptions.CpfInvalid;
import br.com.nityananda.agenda.service.ValidateCpfService;
import org.springframework.stereotype.Service;

@Service
public class ValidateCpfServiceImpl implements ValidateCpfService {
    //The API mentioned was down, so local validation was used
    @Override
    public boolean validate(String cpf) {
        if (cpf == null) {
            throw new CpfInvalid("Invalid CPF");
        }

        cpf = cpf.replaceAll("[^\\d]", "");

        if (cpf.length() != 11 || !cpf.matches("\\d{11}")) {
            throw new CpfInvalid("Invalid CPF");
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int firstDigit = 11 - (sum % 11);
        if (firstDigit > 9) {
            firstDigit = 0;
        }

        if (Character.getNumericValue(cpf.charAt(9)) != firstDigit) {
            throw new CpfInvalid("Invalid CPF");
        }

        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int secondDigit = 11 - (sum % 11);
        if (secondDigit > 9) {
            secondDigit = 0;
        }

        if(Character.getNumericValue(cpf.charAt(10)) == secondDigit){
            return true;
        }
        throw new CpfInvalid("Invalid CPF");

    }
}
