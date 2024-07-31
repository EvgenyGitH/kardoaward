package com.kardoaward.message.controller;

import com.kardoaward.message.dto.MessageWithShortUser;
import com.kardoaward.message.dto.NewMessage;
import com.kardoaward.message.model.Message;
import com.kardoaward.message.service.MessageService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@Validated
@RequestMapping(path = "users/user/messages")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/{senderId}/{recipientId}")
    public Message create(@PathVariable Long senderId,
                          @PathVariable Long recipientId,
                          @RequestBody @Valid NewMessage newMessage) {
        log.info("request: create Message");
        return messageService.createMessage(senderId, recipientId, newMessage);
    }

    @GetMapping("/{senderId}/{recipientId}")
    public List<MessageWithShortUser> getAllMessages(@PathVariable Long senderId,
                                                     @PathVariable Long recipientId) {
        log.info("request: get all Messages");
        return messageService.getAllMessagesById(senderId, recipientId);
    }

    @PatchMapping("/status/{messageId}/{userId}")
    public MessageWithShortUser updateMessageStatus(@PathVariable Long messageId,
                                                    @PathVariable Long userId) {
        log.info("request: status - was read");
        return messageService.updateMessageStatus(messageId, userId);
    }

}
