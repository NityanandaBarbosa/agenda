package br.com.nityananda.agenda.core.controllers.exception;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class HttpError implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer status;
    private String message;
    private LocalDateTime timeStamp;

    public HttpError(Integer status, String message) {
        this.status = status;
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}