package it.milkman.challenge.dao.embeddables;

import it.milkman.challenge.common.Constants;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Column(length = Constants.StringSizingConstants.LARGE, nullable = false)
    private String street;

    @Column(length = Constants.StringSizingConstants.SHORT, nullable = false)
    private String civic;

    @Column(length = Constants.StringSizingConstants.SHORT, nullable = false)
    private String cap;

    @Column(length = Constants.StringSizingConstants.MEDIUM, nullable = false)
    private String city;

    @Column(length = Constants.StringSizingConstants.MEDIUM, nullable = false)
    private String province;

}
