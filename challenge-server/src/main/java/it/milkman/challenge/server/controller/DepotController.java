package it.milkman.challenge.server.controller;

import it.milkman.challenge.dto.depot.DepotDto;
import it.milkman.challenge.server.service.DepotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/depots")
public class DepotController {

    private final DepotService depotService;

    public DepotController(DepotService depotService) {
        this.depotService = depotService;
    }

    @GetMapping
    public Set<DepotDto> getAll() {
        return depotService.getAll();
    }

    @PostMapping("_start-plan")
    public Set<DepotDto> planStart() {
        return depotService.getAll();
    }

}
