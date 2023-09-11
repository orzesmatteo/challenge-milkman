package it.milkman.challenge.mapper;

import it.milkman.challenge.dao.embeddables.Address;
import it.milkman.challenge.dto.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AddressMapper {

    AddressDto daoToDto(Address address);

    Address dtoToDao(AddressDto addressDto);

}
