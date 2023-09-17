package it.milkman.challenge.repository;

import it.milkman.challenge.dao.Order;
import it.milkman.challenge.dao.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    @EntityGraph(attributePaths = {"depot", "supplier", "packages"})
    @Query("""
            SELECT o
            FROM Order o
            WHERE o.id = :orderId
            """)
    Optional<Order> findByIdFull(@Param("orderId") UUID orderId);

    @EntityGraph(attributePaths = {"depot", "packages"})
    @Query("""
            SELECT o
            FROM Order o
            WHERE o.depot.id = :depotId
            AND o.status = 'WAITING'
            ORDER BY o.creation
            """)
    List<Order> findOrdersWaitingInDepot(@Param("depotId") UUID depotId);

    @EntityGraph(attributePaths = {"depot", "packages"})
    @Query("""
            SELECT o
            FROM Order o
            WHERE o.depot.id = :depotId
            AND o.status = 'STARTED'
            AND o.planStart IS NOT NULL
            ORDER BY o.creation
            """)
    List<Order> findOrdersStartedInDepot(@Param("depotId") UUID depotId);

    @EntityGraph(attributePaths = {"depot", "supplier", "packages"})
    @Query("""
            SELECT o
            FROM Order o
            WHERE o.depot.id = :depotId
            AND o.status = :status
            """)
    Page<Order> findByDepotAndStatus(@Param("depotId") UUID depotId, @Param("status") OrderStatus status, Pageable pageable);


}
