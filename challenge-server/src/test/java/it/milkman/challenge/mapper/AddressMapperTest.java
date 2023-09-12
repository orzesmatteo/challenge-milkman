package it.milkman.challenge.mapper;

import it.milkman.challenge.dao.embeddables.Address;
import it.milkman.challenge.dto.AddressDto;
import it.milkman.challenge.server.ChallengeApplication;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ChallengeApplication.class)
public class AddressMapperTest {

    @Test
    void verifyMapper() {
        AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);
        Address address = new Address("Viale Ferri", "13", "47833", "Morciano di Romagna", "Rimini");
        AddressDto addressDto = new AddressDto("Viale Ferri", "13", "47833", "Morciano di Romagna", "Rimini");
        AddressDto generatedDto = addressMapper.daoToDto(address);
        Address generatedDao = addressMapper.dtoToDao(addressDto);
        assert (generatedDto.equals(addressDto));
        assert (generatedDao.equals(address));
    }

}
