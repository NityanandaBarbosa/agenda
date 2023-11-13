package br.com.nityananda.agenda.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record SessionRecordDto(@NotBlank String agenda_id, @Nullable LocalDateTime end_session) {
}
