package it.milkman.challenge.dao;

import it.milkman.challenge.common.Constants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "orders") //In order to avoid conflicts with order keyword in postgresql
@AllArgsConstructor
@Getter
@Setter
public class Order extends BaseEntity {

    @Column(length = Constants.StringSizingConstants.XLARGE)
    private String notes;

    @OneToMany(targetEntity = Package.class, mappedBy = "order")
    private Set<Package> packages;

}
