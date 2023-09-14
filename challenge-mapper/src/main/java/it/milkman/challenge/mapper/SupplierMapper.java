package it.milkman.challenge.mapper;

import it.milkman.challenge.dao.Supplier;
import it.milkman.challenge.dto.order.OrderDto;
import it.milkman.challenge.dto.supplier.SupplierDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SupplierMapper {

    SupplierDto daoToDto(Supplier supplier);

    Supplier dtoToDao(SupplierDto supplierDto);

}
