package br.com.nityananda.agenda.associate.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record AssociateRecordDto(@NotBlank String name, @NotBlank String cpf, @NotNull Date birthdate) {
}
