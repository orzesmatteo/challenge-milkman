package it.milkman.challenge.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "orders") //In order to avoid conflicts with order keyword in postgresql
@AllArgsConstructor
@Getter
@Setter
public class Order extends BaseEntity {

    private String notes;

}
