package com.kardoaward.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
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
    private String password;
    @NotBlank
    private String nickname;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String middle_name;
    @NotBlank
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
