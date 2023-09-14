package it.milkman.challenge.dao.embeddables;

import it.milkman.challenge.common.Constants;
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
public class Address {

    @Column(length = Constants.StringSizingConstants.LARGE, nullable = false)
    private String street;

    @Column(length = Constants.StringSizingConstants.SHORT, nullable = false)
    private String civic;

    @Column(length = Constants.StringSizingConstants.SHORT, nullable = false)
    private String cap;

    @Column(length = Constants.StringSizingConstants.LARGE, nullable = false)
    private String city;

    @Column(length = Constants.StringSizingConstants.LARGE, nullable = false)
    private String province;

}
