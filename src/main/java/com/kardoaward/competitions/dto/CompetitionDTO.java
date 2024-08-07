package com.kardoaward.competitions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionDTO {
    private String competitionType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<String> directions;
    private Set<String> participationTypes;
    private Set<String> locations;
}
