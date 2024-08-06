package com.kardoaward.competitions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO {
    private Long competitionId;
    private Set<String> directions;
}
