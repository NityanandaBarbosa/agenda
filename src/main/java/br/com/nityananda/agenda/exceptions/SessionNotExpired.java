package br.com.nityananda.agenda.exceptions;

public class SessionNotExpired extends SessionNotFound{
    public SessionNotExpired(String message) {
        super(message);
    }
}
