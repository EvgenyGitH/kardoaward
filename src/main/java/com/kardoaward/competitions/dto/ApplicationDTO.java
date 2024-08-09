package com.kardoaward.competitions.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO {
    @NotNull
    private Long competitionId;
    @NotNull
    private Long userId;
    private String applicationType;
    private String status;

    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthday;
    private String phone;
    private String city;
    private String citizenship;
    private String gender;
    private List<String> directions;
}
