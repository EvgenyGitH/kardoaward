package com.kardoaward.competitions.service.impl;

import com.kardoaward.competitions.dto.CompetitionDTO;
import com.kardoaward.competitions.mapper.CompetitionMapper;
import com.kardoaward.competitions.model.Competition;
import com.kardoaward.competitions.model.Direction;
import com.kardoaward.competitions.model.ParticipationType;
import com.kardoaward.competitions.repository.CompetitionRepository;
import com.kardoaward.competitions.repository.DirectionRepository;
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

        return competitionRepository.save(CompetitionMapper.competitionFromDTO(competitionDTO, participationTypes, directions));
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
}