package it.milkman.challenge.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant creation;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant lastUpdate;

}
