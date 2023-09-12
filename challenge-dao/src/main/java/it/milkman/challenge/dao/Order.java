package it.milkman.challenge.dao;

import it.milkman.challenge.common.Constants;
import it.milkman.challenge.dao.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "orders") //In order to avoid conflicts with order keyword in postgresql
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Depot depot;

    @Column(length = Constants.StringSizingConstants.XLARGE)
    private String notes;

    @OneToMany(targetEntity = Package.class, mappedBy = "order")
    private Set<Package> packages;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = Constants.StringSizingConstants.MEDIUM)
    private OrderStatus status;

    private Instant planStart;

    private Instant deliveryStart;

    private Instant deliveryEnd;

}
