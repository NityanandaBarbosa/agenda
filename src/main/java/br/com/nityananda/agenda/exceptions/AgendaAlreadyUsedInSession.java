package br.com.nityananda.agenda.exceptions;

public class AgendaAlreadyUsedInSession extends AgendaException{
    public AgendaAlreadyUsedInSession(String message) {
        super(message);
    }
}
