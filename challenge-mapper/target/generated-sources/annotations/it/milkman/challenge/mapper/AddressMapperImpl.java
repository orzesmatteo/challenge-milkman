package it.milkman.challenge.mapper;

import it.milkman.challenge.dao.embeddables.Address;
import it.milkman.challenge.dto.AddressDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-14T22:26:00+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public AddressDto daoToDto(Address address) {
        if ( address == null ) {
            return null;
        }

        String street = null;
        String civic = null;
        String cap = null;
        String city = null;
        String province = null;

        street = address.getStreet();
        civic = address.getCivic();
        cap = address.getCap();
        city = address.getCity();
        province = address.getProvince();

        AddressDto addressDto = new AddressDto( street, civic, cap, city, province );

        return addressDto;
    }

    @Override
    public Address dtoToDao(AddressDto addressDto) {
        if ( addressDto == null ) {
            return null;
        }

        Address address = new Address();

        address.setStreet( addressDto.street() );
        address.setCivic( addressDto.civic() );
        address.setCap( addressDto.cap() );
        address.setCity( addressDto.city() );
        address.setProvince( addressDto.province() );

        return address;
    }
}
