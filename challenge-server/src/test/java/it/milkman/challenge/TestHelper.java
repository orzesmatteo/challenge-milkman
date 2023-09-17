package it.milkman.challenge;

import it.milkman.challenge.dao.Depot;
import it.milkman.challenge.dao.embeddables.Address;
import it.milkman.challenge.dao.embeddables.Coordinates;
import it.milkman.challenge.dto.AddressDto;
import it.milkman.challenge.dto.CoordinatesDto;

public final class TestHelper {

    public static Address getAddressForTests() {
        return new Address("Viale Ferri", "13", "47833", "Morciano di Romagna", "Rimini");
    }

    public static AddressDto getAddressDtoForTests() {
        return new AddressDto("Viale Ferri", "13", "47833", "Morciano di Romagna", "Rimini");
    }

    public static Coordinates getCoordinatesForTests() {
        return new Coordinates(43.911153, 12.647942);
    }

    public static CoordinatesDto getCoordinatesDtoForTests() {
        return new CoordinatesDto(43.911153, 12.647942);
    }

    public static Depot getFirstDepotForTests() {
        return new Depot("warehouseName1", TestHelper.getAddressForTests(), TestHelper.getCoordinatesForTests());
    }

    public static Depot getSecondDepotForTests() {
        return new Depot("warehouseName2", TestHelper.getAddressForTests(), TestHelper.getCoordinatesForTests());
    }

}
