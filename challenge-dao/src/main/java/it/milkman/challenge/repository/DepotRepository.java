package it.milkman.challenge.repository;

import it.milkman.challenge.dao.Depot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DepotRepository extends JpaRepository<Depot, UUID> {
}
