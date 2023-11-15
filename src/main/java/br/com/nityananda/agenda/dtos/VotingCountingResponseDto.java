package br.com.nityananda.agenda.dtos;

public class VotingCountingResponseDto {

    final int num_yes;
    final int num_no;

    public VotingCountingResponseDto(int numYes, int numNo) {
        num_yes = numYes;
        num_no = numNo;
    }

    public int getNum_yes() {
        return num_yes;
    }

    public int getNum_no() {
        return num_no;
    }


}
