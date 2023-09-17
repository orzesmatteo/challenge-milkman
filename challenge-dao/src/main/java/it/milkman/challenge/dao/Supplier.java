package it.milkman.challenge.dao;

import it.milkman.challenge.common.Constants;
import jakarta.persistence.Column;
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
public class Supplier extends BaseEntity {

    @Column(unique = true, nullable = false, length = Constants.StringSizingConstants.MEDIUM)
    private String name;

}
