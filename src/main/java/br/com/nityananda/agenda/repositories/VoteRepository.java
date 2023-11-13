package br.com.nityananda.agenda.repositories;

import br.com.nityananda.agenda.models.Agenda;
import br.com.nityananda.agenda.models.Associate;
import br.com.nityananda.agenda.models.Session;
import br.com.nityananda.agenda.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VoteRepository extends JpaRepository<Vote, UUID> {
    Vote findByAssociateAndSession(Associate associate, Session session);
    List<Vote> findAllBySession(Session session);
}
