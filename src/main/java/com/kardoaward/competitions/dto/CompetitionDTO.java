package com.kardoaward.competitions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionDTO {
    private String competitionType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Set<String> directions;
    private Set<String> participationTypes;
    private Set<String> locations;
}