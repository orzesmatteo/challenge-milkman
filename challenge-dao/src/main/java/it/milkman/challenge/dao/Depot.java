package it.milkman.challenge.dao;

import it.milkman.challenge.common.Constants;
import it.milkman.challenge.dao.embeddables.Address;
import it.milkman.challenge.dao.embeddables.Coordinates;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Depot extends BaseEntity {

    @OneToMany(targetEntity = Order.class, mappedBy = "depot")
    private Set<Order> orders;

    @Column(unique = true, nullable = false, length = Constants.StringSizingConstants.MEDIUM)
    private String warehouseName;

    @Embedded
    private Address address;

    @Embedded
    private Coordinates coordinates;

}
