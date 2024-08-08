package com.kardoaward.competitions.mapper;

import com.kardoaward.competitions.dto.ApplicationResponseDTO;
import com.kardoaward.competitions.model.Application;
import com.kardoaward.competitions.model.Competition;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ApplicationMapper {
    public ApplicationResponseDTO convertToDTO(Application application) {
        Competition competition = application.getCompetition();
        return new ApplicationResponseDTO(
                application.getId(),
                competition.getCompetitionType(),
                competition.getStartDate(),
                competition.getEndDate(),
                application.getApplicationType(),
                application.getStatus()
        );
    }
}
