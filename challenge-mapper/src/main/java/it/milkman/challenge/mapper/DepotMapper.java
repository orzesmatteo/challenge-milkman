package it.milkman.challenge.mapper;

import it.milkman.challenge.dao.Depot;
import it.milkman.challenge.dao.Order;
import it.milkman.challenge.dto.depot.DepotDto;
import it.milkman.challenge.dto.order.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {AddressMapper.class, CoordinatesMapper.class}
)
public interface DepotMapper {

    @Mapping(target = "addressDto", source = "address")
    @Mapping(target = "coordinatesDto", source = "coordinates")
    DepotDto daoToDto(Depot depot);

    @Mapping(target = "address", source = "addressDto")
    @Mapping(target = "coordinates", source = "coordinatesDto")
    Depot dtoToDao(DepotDto depotDto);

}