package it.milkman.challenge.server;

import it.milkman.challenge.dao.Depot;
import it.milkman.challenge.dao.embeddables.Address;
import it.milkman.challenge.dao.embeddables.Coordinates;
import it.milkman.challenge.dto.AddressDto;
import it.milkman.challenge.dto.CoordinatesDto;
import it.milkman.challenge.dto.depot.DepotDto;
import it.milkman.challenge.mapper.DepotMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@SpringBootTest(classes = ChallengeApplication.class)
class ChallengeServerApplicationTests {

    @Autowired
    DepotMapper depotMapper;

    @Test
    void contextLoads() {
        assert (true);
    }

}
