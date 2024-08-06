package com.kardoaward.competitions.mapper;

import com.kardoaward.competitions.dto.CompetitionDTO;
import com.kardoaward.competitions.model.Competition;
import com.kardoaward.competitions.model.Direction;
import com.kardoaward.competitions.model.ParticipationType;
import lombok.experimental.UtilityClass;

import java.util.Set;

@UtilityClass
public class CompetitionMapper {
    public Competition competitionFromDTO(CompetitionDTO competitionDTO, Set<ParticipationType> participationTypes, Set<Direction> directions){
        Competition competition = new Competition();
        competition.setCompetitionType(competitionDTO.getCompetitionType());
        competition.setStartDate(competitionDTO.getStartDate());
        competition.setEndDate(competitionDTO.getEndDate());
        competition.setParticipationTypes(participationTypes);
        competition.setDirections(directions);
        competition.setLocations(competitionDTO.getLocations());
        return competition;
    }
}
