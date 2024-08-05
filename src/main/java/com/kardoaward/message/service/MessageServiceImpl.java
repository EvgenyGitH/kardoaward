package com.kardoaward.message.service;

import com.kardoaward.exception.DataConflictException;
import com.kardoaward.exception.NotFoundException;
import com.kardoaward.message.dto.MessageWithShortUser;
import com.kardoaward.message.dto.NewMessage;
import com.kardoaward.message.mapper.MessageMapper;
import com.kardoaward.message.model.Message;
import com.kardoaward.message.repository.MessageRepository;
import com.kardoaward.user.model.User;
import com.kardoaward.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Message createMessage(Long senderId, Long recipientId, NewMessage newMessage) {
        User sender = userRepository.findById(senderId).orElseThrow(() -> new NotFoundException("User ID: " + senderId + " not found"));
        User recipient = userRepository.findById(recipientId).orElseThrow(() -> new NotFoundException("User ID: " + recipientId + " not found"));
        Message message = Message.builder()
                .sender(sender)
                .recipient(recipient)
                .text(newMessage.getText())
                .dateMessage(LocalDateTime.now())
                .wasRead(false)
                .build();
        Message messageSaved;
        try {
            messageSaved = messageRepository.save(message);
        } catch (DataIntegrityViolationException e) {
            log.error("Сообщение не может быть пустым");
            throw new DataConflictException("Сообщение не может быть пустым");
        }
        return messageSaved;
    }

    @Override
    public List<MessageWithShortUser> getAllMessagesById(Long senderId, Long recipientId) {
        if (!userRepository.existsById(senderId)) {
            throw new NotFoundException("User ID: " + senderId + " not found");
        }
        if (!userRepository.existsById(recipientId)) {
            throw new NotFoundException("User ID: " + recipientId + " not found");
        }
        List<Message> messages = messageRepository.findAllBySenderIdAndRecipientId(senderId, recipientId);
        return MessageMapper.toListWithShortUserFromMessage(messages);
    }

    @Override
    public MessageWithShortUser updateMessageStatus(Long messageId, Long userId) {
        Message message = messageRepository.findById(messageId).orElseThrow(() -> new NotFoundException("Message ID: " + messageId + " not found"));
        if (!message.getRecipient().getId().equals(userId)) {
            throw new DataConflictException("Not correct data");
        }
        message.setWasRead(true);
        return MessageMapper.WithShortUserfromMessage(messageRepository.save(message));
    }


}
