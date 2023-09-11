package it.milkman.challenge.dao;

import it.milkman.challenge.common.Constants;
import it.milkman.challenge.dao.embeddables.Address;
import it.milkman.challenge.dao.embeddables.Coordinates;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Getter
@Setter
public class Package extends BaseEntity {

    @Embedded
    private Address address;

    //TODO provide a mock implementation that resolve coordinates based on address
    @Embedded
    private Coordinates resolvedCoordinates;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Order order;

    @Column(length = Constants.StringSizingConstants.LARGE)
    private String notesForDelivery;

}
