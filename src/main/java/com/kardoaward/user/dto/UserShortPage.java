package com.kardoaward.user.dto;

import com.kardoaward.user.model.State;
import com.kardoaward.user.model.Style;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserShortPage {
    private Long id;
    private String nickname;
    private String firstName;
    private String lastName;
    private String photoLink;
    private Style style;
    private State state;

}
