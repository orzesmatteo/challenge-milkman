package it.milkman.challenge.mapper;

import it.milkman.challenge.dao.Depot;
import it.milkman.challenge.dto.DepotDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DepotMapper {

    @Mapping(target = "latitude", ignore = true) //TODO
    @Mapping(target = "longitude", ignore = true)
        //TODO
    DepotDto daoToDto(Depot depot);

    @Mapping(target = "coordinates", ignore = true)
        //TODO
    Depot dtoToDao(DepotDto depotDto);

}