package com.kardoaward.user.dto;

import com.kardoaward.user.model.State;
import com.kardoaward.user.model.Style;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPage {

    //todo возможно необходимо будет добавить userId для фронта
    private String nickname;
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthday;
    private String country;
    private String region;
    private String city;

    private String photoLink;
    private String backgroundLink;
    private String pageLink;
    private Style style;
    private String aboutMe;
    private State state;

    private List<UserShortPage> iFollowings;
    private List<UserShortPage> myFollowers;


}
