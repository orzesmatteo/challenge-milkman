package it.milkman.challenge.dao;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    protected Instant creation;

    @LastModifiedDate
    @Column(nullable = false)
    protected Instant lastUpdate;

}
