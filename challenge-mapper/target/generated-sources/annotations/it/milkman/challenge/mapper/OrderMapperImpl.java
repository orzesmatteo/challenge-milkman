package it.milkman.challenge.mapper;

import it.milkman.challenge.dao.Order;
import it.milkman.challenge.dao.Package;
import it.milkman.challenge.dao.embeddables.Address;
import it.milkman.challenge.dao.embeddables.Coordinates;
import it.milkman.challenge.dto.AddressDto;
import it.milkman.challenge.dto.CoordinatesDto;
import it.milkman.challenge.dto.order.OrderDto;
import it.milkman.challenge.dto.packages.PackageDto;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-11T22:42:14+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDto daoToDto(Order order) {
        if ( order == null ) {
            return null;
        }

        UUID id = null;
        Instant creation = null;
        Instant lastUpdate = null;
        String notes = null;
        Set<PackageDto> packages = null;

        id = order.getId();
        creation = order.getCreation();
        lastUpdate = order.getLastUpdate();
        notes = order.getNotes();
        packages = packageSetToPackageDtoSet( order.getPackages() );

        OrderDto orderDto = new OrderDto( id, creation, lastUpdate, notes, packages );

        return orderDto;
    }

    @Override
    public Order dtoToDao(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        String notes = null;
        Set<Package> packages = null;

        notes = orderDto.notes();
        packages = packageDtoSetToPackageSet( orderDto.packages() );

        Order order = new Order( notes, packages );

        order.setId( orderDto.id() );
        order.setCreation( orderDto.creation() );
        order.setLastUpdate( orderDto.lastUpdate() );

        return order;
    }

    protected PackageDto packageToPackageDto(Package package1) {
        if ( package1 == null ) {
            return null;
        }

        UUID id = null;
        Instant creation = null;
        Instant lastUpdate = null;
        String notesForDelivery = null;

        id = package1.getId();
        creation = package1.getCreation();
        lastUpdate = package1.getLastUpdate();
        notesForDelivery = package1.getNotesForDelivery();

        AddressDto addressDto = null;
        CoordinatesDto coordinatesDto = null;

        PackageDto packageDto = new PackageDto( id, creation, lastUpdate, addressDto, coordinatesDto, notesForDelivery );

        return packageDto;
    }

    protected Set<PackageDto> packageSetToPackageDtoSet(Set<Package> set) {
        if ( set == null ) {
            return null;
        }

        Set<PackageDto> set1 = new LinkedHashSet<PackageDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Package package1 : set ) {
            set1.add( packageToPackageDto( package1 ) );
        }

        return set1;
    }

    protected Package packageDtoToPackage(PackageDto packageDto) {
        if ( packageDto == null ) {
            return null;
        }

        String notesForDelivery = null;

        notesForDelivery = packageDto.notesForDelivery();

        Address address = null;
        Coordinates resolvedCoordinates = null;
        Order order = null;

        Package package1 = new Package( address, resolvedCoordinates, order, notesForDelivery );

        package1.setId( packageDto.id() );
        package1.setCreation( packageDto.creation() );
        package1.setLastUpdate( packageDto.lastUpdate() );

        return package1;
    }

    protected Set<Package> packageDtoSetToPackageSet(Set<PackageDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Package> set1 = new LinkedHashSet<Package>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PackageDto packageDto : set ) {
            set1.add( packageDtoToPackage( packageDto ) );
        }

        return set1;
    }
}
