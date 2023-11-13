package br.com.nityananda.agenda.models;

import br.com.nityananda.agenda.dtos.SessionResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_SESSION")
public class Session implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;


    @OneToOne
    @JoinColumn(name = "agenda_id", referencedColumnName = "id", unique = true)
    private Agenda agenda;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "begin_session", nullable = false)
    final private LocalDateTime beginSession = LocalDateTime.now();

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "end_session", nullable = false)
    private LocalDateTime endSession;

    @OneToMany(mappedBy = "session")
    private Set<Vote> votes = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public LocalDateTime getBeginSession() {
        return beginSession;
    }

    public LocalDateTime getEndSession() {
        return endSession;
    }

    public void setEndSession(LocalDateTime endSession) {
        this.endSession = endSession;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    public SessionResponseDto toGetDto(){
        return new SessionResponseDto(
            this.getId().toString(),
            this.getAgenda(),
            this.getBeginSession(),
            this.getEndSession()
        );
    }
}
