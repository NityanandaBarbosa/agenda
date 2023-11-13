package br.com.nityananda.agenda.repositories;

import br.com.nityananda.agenda.models.Session;
import br.com.nityananda.agenda.models.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {
    Session getByAgenda(Agenda agenda);
}
