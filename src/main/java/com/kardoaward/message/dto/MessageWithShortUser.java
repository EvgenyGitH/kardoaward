package com.kardoaward.message.dto;

import com.kardoaward.user.dto.UserShortPage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageWithShortUser {

    private Long id;
    private UserShortPage sender;
    private UserShortPage recipient;
    private String text;
    private LocalDateTime dateMessage;
    private boolean wasRead;


}
