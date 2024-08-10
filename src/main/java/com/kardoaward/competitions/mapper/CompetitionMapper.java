package com.kardoaward.competitions.mapper;

import com.kardoaward.competitions.dto.CompetitionDTO;
import com.kardoaward.competitions.model.Competition;
import com.kardoaward.competitions.model.Direction;
import com.kardoaward.competitions.model.Location;
import com.kardoaward.competitions.model.ParticipationType;
import lombok.experimental.UtilityClass;

import java.util.Set;

@UtilityClass
public class CompetitionMapper {
    public Competition competitionFromDTO(CompetitionDTO competitionDTO, Set<ParticipationType> participationTypes, Set<Direction> directions, Set<Location> locations) {
        Competition competition = new Competition();
        competition.setCompetitionType(competitionDTO.getCompetitionType());
        competition.setStartDate(competitionDTO.getStartDate());
        competition.setEndDate(competitionDTO.getEndDate());
        competition.setParticipationTypes(participationTypes);
        competition.setDirections(directions);
        competition.setLocations(locations);
        return competition;
    }

    public void updateCompetitionFromDTO(Competition competition, CompetitionDTO competitionDTO, Set<ParticipationType> participationTypes, Set<Direction> directions, Set<Location> locations) {
        if (competitionDTO.getCompetitionType() != null) {
            competition.setCompetitionType(competitionDTO.getCompetitionType());
        }
        if (competitionDTO.getStartDate() != null) {
            competition.setStartDate(competitionDTO.getStartDate());
        }
        if (competitionDTO.getEndDate() != null) {
            competition.setEndDate(competitionDTO.getEndDate());
        }
        if (competitionDTO.getParticipationTypes() != null) {
            competition.setParticipationTypes(participationTypes);
        }
        if (competitionDTO.getDirections() != null) {
            competition.setDirections(directions);
        }
        if (competitionDTO.getLocations() != null) {
            competition.setLocations(locations);
        }
    }
}
