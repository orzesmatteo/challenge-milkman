package it.milkman.challenge.dao.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Coordinates {

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

}
