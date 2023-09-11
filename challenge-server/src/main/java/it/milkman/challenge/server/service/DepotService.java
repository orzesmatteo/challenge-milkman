package it.milkman.challenge.server.service;

import it.milkman.challenge.dto.depot.DepotDto;
import it.milkman.challenge.mapper.DepotMapper;
import it.milkman.challenge.repository.DepotRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DepotService {

    private final DepotRepository depotRepository;

    private final DepotMapper depotMapper;

    public DepotService(DepotRepository depotRepository, DepotMapper depotMapper) {
        this.depotRepository = depotRepository;
        this.depotMapper = depotMapper;
    }

    public Set<DepotDto> getAll() {
        return depotRepository.findAll().stream().map(depotMapper::daoToDto).collect(Collectors.toSet());
    }

}
