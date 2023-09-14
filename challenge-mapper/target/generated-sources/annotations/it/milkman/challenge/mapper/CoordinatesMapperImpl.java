package it.milkman.challenge.mapper;

import it.milkman.challenge.dao.embeddables.Coordinates;
import it.milkman.challenge.dto.CoordinatesDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-14T22:26:00+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class CoordinatesMapperImpl implements CoordinatesMapper {

    @Override
    public CoordinatesDto daoToDto(Coordinates coordinates) {
        if ( coordinates == null ) {
            return null;
        }

        double latitude = 0.0d;
        double longitude = 0.0d;

        latitude = coordinates.getLatitude();
        longitude = coordinates.getLongitude();

        CoordinatesDto coordinatesDto = new CoordinatesDto( latitude, longitude );

        return coordinatesDto;
    }

    @Override
    public Coordinates dtoToDao(CoordinatesDto coordinatesDto) {
        if ( coordinatesDto == null ) {
            return null;
        }

        Coordinates coordinates = new Coordinates();

        coordinates.setLatitude( coordinatesDto.latitude() );
        coordinates.setLongitude( coordinatesDto.longitude() );

        return coordinates;
    }
}
