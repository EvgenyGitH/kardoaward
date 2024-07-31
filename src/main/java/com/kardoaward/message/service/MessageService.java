package com.kardoaward.message.service;

import com.kardoaward.message.dto.MessageWithShortUser;
import com.kardoaward.message.dto.NewMessage;
import com.kardoaward.message.model.Message;

import java.util.List;

public interface MessageService {
    Message createMessage(Long senderId, Long recipientId, NewMessage newMessage);
    List<MessageWithShortUser> getAllMessagesById(Long senderId, Long recipientId);
    MessageWithShortUser updateMessageStatus(Long messageId, Long userId);

}
