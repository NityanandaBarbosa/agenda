package br.com.nityananda.agenda.exceptions;

import br.com.nityananda.agenda.exceptions.AgendaException;

public class CpfInvalid extends AgendaException {
    public CpfInvalid(String message) {
        super(message);
    }
}
