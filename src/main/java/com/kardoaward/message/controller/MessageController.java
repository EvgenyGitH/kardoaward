package com.kardoaward.message.controller;

import com.kardoaward.message.dto.MessageWithShortUser;
import com.kardoaward.message.dto.NewMessage;
import com.kardoaward.message.model.Message;
import com.kardoaward.message.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag (name = "Сообщения пользователей", description = "Управление сообщениями пользователя")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/{senderId}/{recipientId}")
    @Operation(summary = "Создание сообщения")
    public Message create(@PathVariable Long senderId,
                          @PathVariable Long recipientId,
                          @RequestBody @Valid NewMessage newMessage) {
        log.info("request: create Message");
        return messageService.createMessage(senderId, recipientId, newMessage);
    }

    @GetMapping("/{senderId}/{recipientId}")
    @Operation(summary = "Получение списка сообщений Отправителя и Получателя")
    public List<MessageWithShortUser> getAllMessages(@PathVariable Long senderId,
                                                     @PathVariable Long recipientId) {
        log.info("request: get all Messages");
        return messageService.getAllMessagesById(senderId, recipientId);
    }

    @PatchMapping("/status/{messageId}/{userId}")
    @Operation(summary = "Изменение статуса сообщения: не прочитано/прочитано")
    public MessageWithShortUser updateMessageStatus(@PathVariable Long messageId,
                                                    @PathVariable Long userId) {
        log.info("request: status - was read");
        return messageService.updateMessageStatus(messageId, userId);
    }

}
