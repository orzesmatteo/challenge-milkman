package it.milkman.challenge.dao;

import it.milkman.challenge.dao.embeddables.Coordinates;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Setter
public class Depot extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String warehouseName;

    @Embedded
    private Coordinates coordinates;

}
