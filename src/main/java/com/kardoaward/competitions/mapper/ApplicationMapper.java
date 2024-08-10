package com.kardoaward.competitions.mapper;

import com.kardoaward.competitions.dto.ApplicationResponseDTO;
import com.kardoaward.competitions.model.Application;
import com.kardoaward.competitions.model.Competition;
import com.kardoaward.competitions.model.Direction;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ApplicationMapper {
    public static ApplicationResponseDTO convertToDTO(Application application) {
        Competition competition = application.getCompetition();

        List<String> directions = application.getDirections().stream()
                .map(Direction::getName)
                .collect(Collectors.toList());

        return new ApplicationResponseDTO(
                application.getId(),
                competition.getCompetitionType(),
                competition.getStartDate(),
                competition.getEndDate(),
                application.getApplicationType(),
                application.getStatus(),
                directions
        );
    }
}
