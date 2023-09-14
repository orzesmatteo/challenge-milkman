package it.milkman.challenge.repository;

import it.milkman.challenge.dao.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    @EntityGraph(attributePaths = {"depot", "supplier", "packages"})
    @Query("SELECT o FROM Order o WHERE o.id = :orderId")
    Optional<Order> findByIdFull(@Param("orderId") UUID orderId);

}
