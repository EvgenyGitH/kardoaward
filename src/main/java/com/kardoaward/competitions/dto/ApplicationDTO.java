package com.kardoaward.competitions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO {
    private Long competitionId;
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
    private String pageLink;
}
