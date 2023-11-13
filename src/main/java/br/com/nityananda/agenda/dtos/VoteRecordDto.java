package br.com.nityananda.agenda.dtos;

import br.com.nityananda.agenda.enums.VoteOptions;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VoteRecordDto(@NotBlank String session_id, @NotBlank String cpf, @NotNull VoteOptions vote_option) {
}
