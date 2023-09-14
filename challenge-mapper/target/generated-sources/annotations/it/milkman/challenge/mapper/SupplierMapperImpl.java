package it.milkman.challenge.mapper;

import it.milkman.challenge.dao.Supplier;
import it.milkman.challenge.dto.supplier.SupplierDto;
import java.time.Instant;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-14T22:26:00+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class SupplierMapperImpl implements SupplierMapper {

    @Override
    public SupplierDto daoToDto(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }

        UUID id = null;
        Instant creation = null;
        Instant lastUpdate = null;
        String name = null;

        id = supplier.getId();
        creation = supplier.getCreation();
        lastUpdate = supplier.getLastUpdate();
        name = supplier.getName();

        SupplierDto supplierDto = new SupplierDto( id, creation, lastUpdate, name );

        return supplierDto;
    }

    @Override
    public Supplier dtoToDao(SupplierDto supplierDto) {
        if ( supplierDto == null ) {
            return null;
        }

        Supplier supplier = new Supplier();

        supplier.setId( supplierDto.id() );
        supplier.setCreation( supplierDto.creation() );
        supplier.setLastUpdate( supplierDto.lastUpdate() );
        supplier.setName( supplierDto.name() );

        return supplier;
    }
}
