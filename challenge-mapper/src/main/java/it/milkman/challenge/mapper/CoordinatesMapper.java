package it.milkman.challenge.mapper;

import it.milkman.challenge.dao.embeddables.Coordinates;
import it.milkman.challenge.dto.CoordinatesDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CoordinatesMapper {

    CoordinatesDto daoToDto(Coordinates coordinates);

    Coordinates dtoToDao(CoordinatesDto coordinatesDto);

}
