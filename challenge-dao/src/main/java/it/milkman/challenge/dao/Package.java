package it.milkman.challenge.dao;

import it.milkman.challenge.common.Constants;
import it.milkman.challenge.dao.embeddables.Address;
import it.milkman.challenge.dao.embeddables.Coordinates;
import it.milkman.challenge.dao.enums.PackageStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Package extends BaseEntity {

    @Embedded
    private Address address;

    //TODO provide a mock implementation that resolve coordinates based on address
    @Embedded
    private Coordinates coordinates;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @Column(length = Constants.StringSizingConstants.LARGE)
    private String notesForDelivery;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = Constants.StringSizingConstants.MEDIUM)
    private PackageStatus status;

    private Instant deliveryDate;

}
