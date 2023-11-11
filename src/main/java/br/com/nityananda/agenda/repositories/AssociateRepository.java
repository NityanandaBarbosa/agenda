package br.com.nityananda.agenda.repositories;

import br.com.nityananda.agenda.models.Associate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, UUID> {
}
