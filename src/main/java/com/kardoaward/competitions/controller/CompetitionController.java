package com.kardoaward.competitions.controller;

import com.kardoaward.competitions.dto.CompetitionDTO;
import com.kardoaward.competitions.model.Competition;
import com.kardoaward.competitions.model.Direction;
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
        Competition competition = competitionService.createCompetition(competitionDTO);
        return ResponseEntity.ok(competition);
    }

    @GetMapping
    public ResponseEntity<List<Competition>> getAllCompetitions() {
        List<Competition> competitions = competitionService.findAll();
        return ResponseEntity.ok(competitions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Competition> getCompetitionById(@PathVariable Long id) {
        Optional<Competition> competition = competitionService.findById(id);
        return competition.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetition(@PathVariable Long id) {
        competitionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/directions")
    public ResponseEntity<Direction> addDirection(@RequestBody String name) {
        Direction direction = competitionService.addDirection(name);
        return ResponseEntity.ok(direction);
    }

    @PostMapping("/participationTypes")
    public ResponseEntity<ParticipationType> addParticipationType(@RequestBody String name) {
        ParticipationType participationType = competitionService.addParticipationType(name);
        return ResponseEntity.ok(participationType);
    }
}
