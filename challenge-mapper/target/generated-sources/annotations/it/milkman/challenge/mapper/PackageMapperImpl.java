package it.milkman.challenge.mapper;

import it.milkman.challenge.dao.Package;
import it.milkman.challenge.dao.enums.PackageStatus;
import it.milkman.challenge.dto.AddressDto;
import it.milkman.challenge.dto.CoordinatesDto;
import it.milkman.challenge.dto.packages.PackageDto;
import it.milkman.challenge.dto.packages.PackageStatusDto;
import java.time.Instant;
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
public class PackageMapperImpl implements PackageMapper {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private CoordinatesMapper coordinatesMapper;

    @Override
    public PackageDto daoToDto(Package packageDao) {
        if ( packageDao == null ) {
            return null;
        }

        UUID id = null;
        Instant creation = null;
        Instant lastUpdate = null;
        AddressDto address = null;
        CoordinatesDto coordinates = null;
        String notesForDelivery = null;
        PackageStatusDto status = null;
        Instant deliveryDate = null;

        id = packageDao.getId();
        creation = packageDao.getCreation();
        lastUpdate = packageDao.getLastUpdate();
        address = addressMapper.daoToDto( packageDao.getAddress() );
        coordinates = coordinatesMapper.daoToDto( packageDao.getCoordinates() );
        notesForDelivery = packageDao.getNotesForDelivery();
        status = packageStatusToPackageStatusDto( packageDao.getStatus() );
        deliveryDate = packageDao.getDeliveryDate();

        PackageDto packageDto = new PackageDto( id, creation, lastUpdate, address, coordinates, notesForDelivery, status, deliveryDate );

        return packageDto;
    }

    @Override
    public Package dtoToDao(PackageDto packageDto) {
        if ( packageDto == null ) {
            return null;
        }

        Package package1 = new Package();

        package1.setId( packageDto.id() );
        package1.setCreation( packageDto.creation() );
        package1.setLastUpdate( packageDto.lastUpdate() );
        package1.setAddress( addressMapper.dtoToDao( packageDto.address() ) );
        package1.setCoordinates( coordinatesMapper.dtoToDao( packageDto.coordinates() ) );
        package1.setNotesForDelivery( packageDto.notesForDelivery() );
        package1.setStatus( packageStatusDtoToPackageStatus( packageDto.status() ) );
        package1.setDeliveryDate( packageDto.deliveryDate() );

        return package1;
    }

    protected PackageStatusDto packageStatusToPackageStatusDto(PackageStatus packageStatus) {
        if ( packageStatus == null ) {
            return null;
        }

        PackageStatusDto packageStatusDto;

        switch ( packageStatus ) {
            case WAITING: packageStatusDto = PackageStatusDto.WAITING;
            break;
            case IN_DELIVERY: packageStatusDto = PackageStatusDto.IN_DELIVERY;
            break;
            case DELIVERED: packageStatusDto = PackageStatusDto.DELIVERED;
            break;
            case LOST: packageStatusDto = PackageStatusDto.LOST;
            break;
            case REJECTED: packageStatusDto = PackageStatusDto.REJECTED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + packageStatus );
        }

        return packageStatusDto;
    }

    protected PackageStatus packageStatusDtoToPackageStatus(PackageStatusDto packageStatusDto) {
        if ( packageStatusDto == null ) {
            return null;
        }

        PackageStatus packageStatus;

        switch ( packageStatusDto ) {
            case WAITING: packageStatus = PackageStatus.WAITING;
            break;
            case IN_DELIVERY: packageStatus = PackageStatus.IN_DELIVERY;
            break;
            case DELIVERED: packageStatus = PackageStatus.DELIVERED;
            break;
            case LOST: packageStatus = PackageStatus.LOST;
            break;
            case REJECTED: packageStatus = PackageStatus.REJECTED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + packageStatusDto );
        }

        return packageStatus;
    }
}
