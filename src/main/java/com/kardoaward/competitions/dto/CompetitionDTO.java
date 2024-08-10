package com.kardoaward.competitions.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CompetitionDTO {
    @NotNull
    private String competitionType;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate endDate;
    @NotNull
    private Set<String> directions;
    @NotNull
    private Set<String> participationTypes;
    @NotNull
    private Set<String> locations;
}
