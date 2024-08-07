package com.kardoaward.competitions.service;

import com.kardoaward.competitions.dto.CompetitionDTO;
import com.kardoaward.competitions.model.Competition;
import com.kardoaward.competitions.model.Direction;
import com.kardoaward.competitions.model.ParticipationType;

import java.util.List;
import java.util.Optional;

public interface CompetitionService {
    Competition createCompetition(CompetitionDTO competitionDTO);

    List<Competition> findAll();

    Optional<Competition> findById(Long id);

    void deleteById(Long id);

    Direction addDirection(String name);

    ParticipationType addParticipationType(String name);
}
