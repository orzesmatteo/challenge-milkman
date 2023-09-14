package it.milkman.challenge.mapper;

import it.milkman.challenge.dao.Order;
import it.milkman.challenge.dto.order.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {AddressMapper.class, CoordinatesMapper.class, DepotMapper.class, PackageMapper.class, SupplierMapper.class}
)
public interface OrderMapper {

    OrderDto daoToDto(Order order);

    Order dtoToDao(OrderDto orderDto);

}
