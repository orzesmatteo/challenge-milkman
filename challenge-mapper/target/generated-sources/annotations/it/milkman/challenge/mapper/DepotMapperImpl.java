package it.milkman.challenge.mapper;

import it.milkman.challenge.dao.Depot;
import it.milkman.challenge.dto.AddressDto;
import it.milkman.challenge.dto.CoordinatesDto;
import it.milkman.challenge.dto.depot.DepotDto;
import java.time.Instant;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-14T22:26:00+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class DepotMapperImpl implements DepotMapper {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private CoordinatesMapper coordinatesMapper;

    @Override
    public DepotDto daoToDto(Depot depot) {
        if ( depot == null ) {
            return null;
        }

        AddressDto addressDto = null;
        CoordinatesDto coordinatesDto = null;
        UUID id = null;
        Instant creation = null;
        Instant lastUpdate = null;
        String warehouseName = null;

        addressDto = addressMapper.daoToDto( depot.getAddress() );
        coordinatesDto = coordinatesMapper.daoToDto( depot.getCoordinates() );
        id = depot.getId();
        creation = depot.getCreation();
        lastUpdate = depot.getLastUpdate();
        warehouseName = depot.getWarehouseName();

        DepotDto depotDto = new DepotDto( id, creation, lastUpdate, warehouseName, addressDto, coordinatesDto );

        return depotDto;
    }

    @Override
    public Depot dtoToDao(DepotDto depotDto) {
        if ( depotDto == null ) {
            return null;
        }

        Depot depot = new Depot();

        depot.setAddress( addressMapper.dtoToDao( depotDto.addressDto() ) );
        depot.setCoordinates( coordinatesMapper.dtoToDao( depotDto.coordinatesDto() ) );
        depot.setId( depotDto.id() );
        depot.setCreation( depotDto.creation() );
        depot.setLastUpdate( depotDto.lastUpdate() );
        depot.setWarehouseName( depotDto.warehouseName() );

        return depot;
    }
}
