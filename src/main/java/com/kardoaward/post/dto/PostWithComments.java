package com.kardoaward.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kardoaward.comment.dto.CommentDto;
import com.kardoaward.user.dto.UserShortPage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.http.fileupload.util.LimitedInputStream;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostWithComments {

    private Long id;
    private UserShortPage user;
    private String link;
    private String text;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime datePost;

    private List<CommentDto>commentDtos;
}

