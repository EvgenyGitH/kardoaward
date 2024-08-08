package com.kardoaward.competitions.service;

import com.kardoaward.competitions.dto.CompetitionDTO;
import com.kardoaward.competitions.dto.DirectionDTO;
import com.kardoaward.competitions.dto.LocationDTO;
import com.kardoaward.competitions.dto.ParticipationTypeDTO;
import com.kardoaward.competitions.mapper.CompetitionSummaryDTO;
import com.kardoaward.competitions.model.Competition;
import com.kardoaward.competitions.model.Direction;
import com.kardoaward.competitions.model.Location;
import com.kardoaward.competitions.model.ParticipationType;

import java.util.List;

public interface CompetitionService {
    Competition createCompetition(CompetitionDTO competitionDTO);

    List<CompetitionSummaryDTO> findAll();

    Competition findById(Long id);

    void deleteById(Long id);

    Direction addDirection(DirectionDTO directionDTO);

    ParticipationType addParticipationType(ParticipationTypeDTO participationTypeDTO);

    Location addLocation(LocationDTO locationDTO);

    List<Location> findAllLocations();

    List<ParticipationType> findAllParticipationTypes();

    List<Direction> findAllDirections();

    Competition updateCompetition(Long id, CompetitionDTO competitionDTO);

    void deleteLocationById(Long id);

    void deleteDirectionById(Long id);

    void deleteParticipationTypeById(Long id);
}
