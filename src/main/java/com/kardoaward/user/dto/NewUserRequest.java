package com.kardoaward.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewUserRequest {

    @NotNull
    @Email
    private String email;
    @NotBlank
    @Size(min = 6)
    private String password;
    private String nickname;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String middle_name;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @NotBlank
    private String country;
    @NotBlank
    private String region;
    @NotBlank
    private String city;

    private String phone;
    private String photoLink;
    private String backgroundLink;
    private String pageLink;
    private String style;
    private String aboutMe;


}
