package com.kardoaward.competitions.controller;

import com.kardoaward.competitions.dto.CompetitionDTO;
import com.kardoaward.competitions.dto.DirectionDTO;
import com.kardoaward.competitions.dto.LocationDTO;
import com.kardoaward.competitions.dto.ParticipationTypeDTO;
import com.kardoaward.competitions.mapper.CompetitionSummaryDTO;
import com.kardoaward.competitions.model.Competition;
import com.kardoaward.competitions.model.Direction;
import com.kardoaward.competitions.model.Location;
import com.kardoaward.competitions.model.ParticipationType;
import com.kardoaward.competitions.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/competitions")
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @PostMapping
    public Competition createCompetition(@RequestBody CompetitionDTO competitionDTO) {
        return competitionService.createCompetition(competitionDTO);
    }

    @GetMapping
    public List<CompetitionSummaryDTO> findAll() {
        return competitionService.findAll();
    }

    @GetMapping("/{id}")
    public Competition findById(@PathVariable Long id) {
        return competitionService.findById(id);
    }

    @PatchMapping("/{id}")
    public Competition updateCompetition(@PathVariable Long id, @RequestBody CompetitionDTO competitionDTO) {
        return competitionService.updateCompetition(id, competitionDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        competitionService.deleteById(id);
    }

    @PostMapping("/directions")
    public Direction addDirection(@RequestBody DirectionDTO directionDTO) {
        return competitionService.addDirection(directionDTO);
    }

    @PostMapping("/participation-types")
    public ParticipationType addParticipationType(@RequestBody ParticipationTypeDTO participationTypeDTO) {
        return competitionService.addParticipationType(participationTypeDTO);
    }

    @PostMapping("/locations")
    public Location addLocation(@RequestBody LocationDTO locationDTO) {
        return competitionService.addLocation(locationDTO);
    }

    @GetMapping("/locations")
    public List<Location> getAllLocations() {
        return competitionService.findAllLocations();
    }

    @DeleteMapping("/locations/{id}")
    public void deleteLocation(@PathVariable Long id) {
        competitionService.deleteLocationById(id);
    }

    @GetMapping("/participation-types")
    public List<ParticipationType> getAllParticipationTypes() {
        return competitionService.findAllParticipationTypes();
    }

    @DeleteMapping("/participation-types/{id}")
    public void deleteParticipationType(@PathVariable Long id) {
        competitionService.deleteParticipationTypeById(id);
    }

    @GetMapping("/directions")
    public List<Direction> getAllDirections() {
        return competitionService.findAllDirections();
    }

    @DeleteMapping("/directions/{id}")
    public void deleteDirection(@PathVariable Long id) {
        competitionService.deleteDirectionById(id);
    }
}