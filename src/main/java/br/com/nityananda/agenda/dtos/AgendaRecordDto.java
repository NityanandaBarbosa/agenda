package br.com.nityananda.agenda.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record AgendaRecordDto(@NotBlank String title, @NotBlank String description) {
}
