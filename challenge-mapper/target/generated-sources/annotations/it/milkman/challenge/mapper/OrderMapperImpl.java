package it.milkman.challenge.mapper;

import it.milkman.challenge.dao.Order;
import it.milkman.challenge.dao.Package;
import it.milkman.challenge.dao.enums.OrderStatus;
import it.milkman.challenge.dto.depot.DepotDto;
import it.milkman.challenge.dto.order.OrderDto;
import it.milkman.challenge.dto.order.OrderStatusDto;
import it.milkman.challenge.dto.packages.PackageDto;
import it.milkman.challenge.dto.supplier.SupplierDto;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-17T15:59:13+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private DepotMapper depotMapper;
    @Autowired
    private PackageMapper packageMapper;
    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public OrderDto daoToDto(Order order) {
        if ( order == null ) {
            return null;
        }

        UUID id = null;
        Instant creation = null;
        Instant lastUpdate = null;
        SupplierDto supplier = null;
        DepotDto depot = null;
        String notes = null;
        Set<PackageDto> packages = null;
        OrderStatusDto status = null;
        Instant planStart = null;
        Instant deliveryStart = null;
        Instant deliveryEnd = null;

        id = order.getId();
        creation = order.getCreation();
        lastUpdate = order.getLastUpdate();
        supplier = supplierMapper.daoToDto( order.getSupplier() );
        depot = depotMapper.daoToDto( order.getDepot() );
        notes = order.getNotes();
        packages = packageSetToPackageDtoSet( order.getPackages() );
        status = orderStatusToOrderStatusDto( order.getStatus() );
        planStart = order.getPlanStart();
        deliveryStart = order.getDeliveryStart();
        deliveryEnd = order.getDeliveryEnd();

        OrderDto orderDto = new OrderDto( id, creation, lastUpdate, supplier, depot, notes, packages, status, planStart, deliveryStart, deliveryEnd );

        return orderDto;
    }

    @Override
    public Order dtoToDao(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        Order order = new Order();

        order.setId( orderDto.id() );
        order.setCreation( orderDto.creation() );
        order.setLastUpdate( orderDto.lastUpdate() );
        order.setSupplier( supplierMapper.dtoToDao( orderDto.supplier() ) );
        order.setDepot( depotMapper.dtoToDao( orderDto.depot() ) );
        order.setNotes( orderDto.notes() );
        order.setPackages( packageDtoSetToPackageSet( orderDto.packages() ) );
        order.setStatus( orderStatusDtoToOrderStatus( orderDto.status() ) );
        order.setPlanStart( orderDto.planStart() );
        order.setDeliveryStart( orderDto.deliveryStart() );
        order.setDeliveryEnd( orderDto.deliveryEnd() );

        return order;
    }

    protected Set<PackageDto> packageSetToPackageDtoSet(Set<Package> set) {
        if ( set == null ) {
            return null;
        }

        Set<PackageDto> set1 = new LinkedHashSet<PackageDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Package package1 : set ) {
            set1.add( packageMapper.daoToDto( package1 ) );
        }

        return set1;
    }

    protected OrderStatusDto orderStatusToOrderStatusDto(OrderStatus orderStatus) {
        if ( orderStatus == null ) {
            return null;
        }

        OrderStatusDto orderStatusDto;

        switch ( orderStatus ) {
            case WAITING: orderStatusDto = OrderStatusDto.WAITING;
            break;
            case STARTED: orderStatusDto = OrderStatusDto.STARTED;
            break;
            case IN_DELIVERY: orderStatusDto = OrderStatusDto.IN_DELIVERY;
            break;
            case DELIVERED: orderStatusDto = OrderStatusDto.DELIVERED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + orderStatus );
        }

        return orderStatusDto;
    }

    protected Set<Package> packageDtoSetToPackageSet(Set<PackageDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Package> set1 = new LinkedHashSet<Package>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PackageDto packageDto : set ) {
            set1.add( packageMapper.dtoToDao( packageDto ) );
        }

        return set1;
    }

    protected OrderStatus orderStatusDtoToOrderStatus(OrderStatusDto orderStatusDto) {
        if ( orderStatusDto == null ) {
            return null;
        }

        OrderStatus orderStatus;

        switch ( orderStatusDto ) {
            case WAITING: orderStatus = OrderStatus.WAITING;
            break;
            case STARTED: orderStatus = OrderStatus.STARTED;
            break;
            case IN_DELIVERY: orderStatus = OrderStatus.IN_DELIVERY;
            break;
            case DELIVERED: orderStatus = OrderStatus.DELIVERED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + orderStatusDto );
        }

        return orderStatus;
    }
}
