package com.kardoaward.competitions.controller;

import com.kardoaward.competitions.dto.CompetitionDTO;
import com.kardoaward.competitions.dto.DirectionDTO;
import com.kardoaward.competitions.dto.LocationDTO;
import com.kardoaward.competitions.dto.ParticipationTypeDTO;
import com.kardoaward.competitions.model.Competition;
import com.kardoaward.competitions.model.Direction;
import com.kardoaward.competitions.model.Location;
import com.kardoaward.competitions.model.ParticipationType;
import com.kardoaward.competitions.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/competitions")
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @PostMapping
    public ResponseEntity<Competition> createCompetition(@RequestBody CompetitionDTO competitionDTO) {
        return ResponseEntity.ok(competitionService.createCompetition(competitionDTO));
    }

    @GetMapping
    public ResponseEntity<List<Competition>> findAll() {
        return ResponseEntity.ok(competitionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Competition> findById(@PathVariable Long id) {
        Optional<Competition> competition = competitionService.findById(id);
        return competition.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        competitionService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/directions")
    public ResponseEntity<Direction> addDirection(@RequestBody DirectionDTO directionDTO) {
        return ResponseEntity.ok(competitionService.addDirection(directionDTO));
    }

    @PostMapping("/participation-types")
    public ResponseEntity<ParticipationType> addParticipationType(@RequestBody ParticipationTypeDTO participationTypeDTO) {
        return ResponseEntity.ok(competitionService.addParticipationType(participationTypeDTO));
    }

    @PostMapping("/locations")
    public ResponseEntity<Location> addLocation(@RequestBody LocationDTO locationDTO) {
        return ResponseEntity.ok(competitionService.addLocation(locationDTO));
    }

    @GetMapping("/locations")
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = competitionService.findAllLocations();
        return ResponseEntity.ok(locations);
    }

    @DeleteMapping("/locations/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        competitionService.deleteLocationById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/participation-types")
    public ResponseEntity<List<ParticipationType>> getAllParticipationTypes() {
        List<ParticipationType> participationTypes = competitionService.findAllParticipationTypes();
        return ResponseEntity.ok(participationTypes);
    }

    @DeleteMapping("/participation-types/{id}")
    public ResponseEntity<Void> deleteParticipationType(@PathVariable Long id) {
        competitionService.deleteParticipationTypeById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/directions")
    public ResponseEntity<List<Direction>> getAllDirections() {
        List<Direction> directions = competitionService.findAllDirections();
        return ResponseEntity.ok(directions);
    }

    @DeleteMapping("/directions/{id}")
    public ResponseEntity<Void> deleteDirection(@PathVariable Long id) {
        competitionService.deleteDirectionById(id);
        return ResponseEntity.ok().build();
    }
}
