package it.milkman.challenge.dao.embeddables;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class Coordinates {

    private double latitude;

    private double longitude;

}
