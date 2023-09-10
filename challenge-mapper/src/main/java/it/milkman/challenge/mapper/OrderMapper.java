package it.milkman.challenge.mapper;

import it.milkman.challenge.dao.Order;
import it.milkman.challenge.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface OrderMapper {

    OrderDto daoToDto(Order order);

    Order dtoToDao(OrderDto orderDto);

}
