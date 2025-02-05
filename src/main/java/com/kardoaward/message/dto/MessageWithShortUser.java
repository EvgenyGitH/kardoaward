package com.kardoaward.message.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateMessage;
    private boolean wasRead;


}
