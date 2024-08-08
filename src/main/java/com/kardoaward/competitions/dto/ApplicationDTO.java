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

    private String email;
    private String password;
    private String nickname;
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthday;
    private String country;
    private String region;
    private String city;
    private String phone;
    private String photoLink;
    private String backgroundLink;
    private String pageLink;
    private String style;
    private String aboutMe;
}
