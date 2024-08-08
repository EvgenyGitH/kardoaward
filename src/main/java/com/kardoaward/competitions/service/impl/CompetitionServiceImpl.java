package com.kardoaward.competitions.service.impl;

import com.kardoaward.competitions.dto.CompetitionDTO;
import com.kardoaward.competitions.dto.DirectionDTO;
import com.kardoaward.competitions.dto.LocationDTO;
import com.kardoaward.competitions.dto.ParticipationTypeDTO;
import com.kardoaward.competitions.mapper.CompetitionMapper;
import com.kardoaward.competitions.model.Competition;
import com.kardoaward.competitions.model.Direction;
import com.kardoaward.competitions.model.Location;
import com.kardoaward.competitions.model.ParticipationType;
import com.kardoaward.competitions.repository.CompetitionRepository;
import com.kardoaward.competitions.repository.DirectionRepository;
import com.kardoaward.competitions.repository.LocationRepository;
import com.kardoaward.competitions.repository.ParticipationTypeRepository;
import com.kardoaward.competitions.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CompetitionServiceImpl implements CompetitionService {
    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private DirectionRepository directionRepository;

    @Autowired
    private ParticipationTypeRepository participationTypeRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Competition createCompetition(CompetitionDTO competitionDTO) {
        Set<Direction> directions = competitionDTO.getDirections().stream()
                .map(name -> directionRepository.findByName(name)
                        .orElseThrow(() -> new RuntimeException("Direction not found: " + name)))
                .collect(Collectors.toSet());

        Set<ParticipationType> participationTypes = competitionDTO.getParticipationTypes().stream()
                .map(name -> participationTypeRepository.findByName(name)
                        .orElseThrow(() -> new RuntimeException("Participation Type not found: " + name)))
                .collect(Collectors.toSet());

        Set<Location> locations = competitionDTO.getLocations().stream()
                .map(name -> locationRepository.findByName(name)
                        .orElseThrow(() -> new RuntimeException("Location not found: " + name)))
                .collect(Collectors.toSet());

        return competitionRepository.save(CompetitionMapper.competitionFromDTO(competitionDTO, participationTypes, directions, locations));
    }

    @Override
    public List<Competition> findAll() {
        return competitionRepository.findAll();
    }

    @Override
    public Optional<Competition> findById(Long id) {
        return competitionRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        competitionRepository.deleteById(id);
    }

    @Override
    public Direction addDirection(DirectionDTO directionDTO) {
        Direction direction = new Direction();
        direction.setName(directionDTO.getName());
        return directionRepository.save(direction);
    }

    @Override
    public ParticipationType addParticipationType(ParticipationTypeDTO participationTypeDTO) {
        ParticipationType participationType = new ParticipationType();
        participationType.setName(participationTypeDTO.getName());
        return participationTypeRepository.save(participationType);
    }

    @Override
    public Location addLocation(LocationDTO locationDTO) {
        Location location = new Location();
        location.setName(locationDTO.getName());
        return locationRepository.save(location);
    }

    @Override
    public Competition updateCompetition(Long id, CompetitionDTO competitionDTO) {
        Competition competition = competitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competition not found: " + id));

        Set<Direction> directions = competitionDTO.getDirections() != null ? competitionDTO.getDirections().stream()
                .map(name -> directionRepository.findByName(name)
                        .orElseThrow(() -> new RuntimeException("Direction not found: " + name)))
                .collect(Collectors.toSet()) : competition.getDirections();

        Set<ParticipationType> participationTypes = competitionDTO.getParticipationTypes() != null ? competitionDTO.getParticipationTypes().stream()
                .map(name -> participationTypeRepository.findByName(name)
                        .orElseThrow(() -> new RuntimeException("Participation Type not found: " + name)))
                .collect(Collectors.toSet()) : competition.getParticipationTypes();

        Set<Location> locations = competitionDTO.getLocations() != null ? competitionDTO.getLocations().stream()
                .map(name -> locationRepository.findByName(name)
                        .orElseThrow(() -> new RuntimeException("Location not found: " + name)))
                .collect(Collectors.toSet()) : competition.getLocations();

        CompetitionMapper.updateCompetitionFromDTO(competition, competitionDTO, participationTypes, directions, locations);
        return competitionRepository.save(competition);
    }

    @Override
    public void deleteLocationById(Long id) {
        locationRepository.deleteById(id);
    }

    @Override
    public void deleteDirectionById(Long id) {
        directionRepository.deleteById(id);
    }

    @Override
    public void deleteParticipationTypeById(Long id) {
        participationTypeRepository.deleteById(id);
    }

    @Override
    public List<Location> findAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public List<ParticipationType> findAllParticipationTypes() {
        return participationTypeRepository.findAll();
    }

    public List<Direction> findAllDirections() {
        return directionRepository.findAll();
    }
}