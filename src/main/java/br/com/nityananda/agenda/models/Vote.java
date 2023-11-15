package br.com.nityananda.agenda.models;

import br.com.nityananda.agenda.enums.VoteOptions;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
public class Vote implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private Session session;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "associate_id", referencedColumnName = "registration")
    private Associate associate;

    @Column(name = "vote", nullable = false)
    private VoteOptions vote;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Associate getAssociate() {
        return associate;
    }

    public void setAssociate(Associate associate) {
        this.associate = associate;
    }

    public VoteOptions getVote() {
        return vote;
    }

    public void setVote(VoteOptions vote) {
        this.vote = vote;
    }
}
