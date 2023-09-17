package it.milkman.challenge.dao;

import it.milkman.challenge.common.Constants;
import it.milkman.challenge.dao.embeddables.Address;
import it.milkman.challenge.dao.embeddables.Coordinates;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Depot extends BaseEntity {

    @Column(unique = true, nullable = false, length = Constants.StringSizingConstants.MEDIUM)
    private String warehouseName;

    @Embedded
    private Address address;

    @Embedded
    private Coordinates coordinates;

}
