package it.milkman.challenge.mapper;

import it.milkman.challenge.dao.Package;
import it.milkman.challenge.dto.packages.PackageDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {AddressMapper.class, CoordinatesMapper.class}
)
public interface PackageMapper {

    PackageDto daoToDto(Package packageDao);

    Package dtoToDao(PackageDto packageDto);

}
