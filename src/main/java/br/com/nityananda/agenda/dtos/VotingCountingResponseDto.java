package br.com.nityananda.agenda.dtos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class VotingCountingResponseDto {

    final int num_yes;
    final int num_no;

    public VotingCountingResponseDto(int numYes, int numNo) {
        num_yes = numYes;
        num_no = numNo;
    }
}
