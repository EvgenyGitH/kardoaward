package com.kardoaward.competitions.mapper;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionSummaryDTO {
    private Long id;
    private String competitionType;
    private LocalDate startDate;
    private LocalDate endDate;
}

