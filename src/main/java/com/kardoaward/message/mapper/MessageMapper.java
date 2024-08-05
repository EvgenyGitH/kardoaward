package com.kardoaward.message.mapper;

import com.kardoaward.message.dto.MessageWithShortUser;
import com.kardoaward.message.model.Message;
import com.kardoaward.user.mapper.UserMapper;

import java.util.List;

public class MessageMapper {
    public static MessageWithShortUser WithShortUserfromMessage(Message message) {
        MessageWithShortUser messageWithShortUser = new MessageWithShortUser();
        messageWithShortUser.setId(message.getId());
        messageWithShortUser.setSender(UserMapper.userToUserShortPage(message.getSender()));
        messageWithShortUser.setRecipient(UserMapper.userToUserShortPage(message.getRecipient()));
        messageWithShortUser.setText(message.getText());
        messageWithShortUser.setDateMessage(message.getDateMessage());
        messageWithShortUser.setWasRead(message.isWasRead());
        return messageWithShortUser;
    }

    public static List<MessageWithShortUser> toListWithShortUserFromMessage(List<Message> messages) {
        return messages.stream()
                        .map(message -> {
                            MessageWithShortUser messageWithShortUser = new MessageWithShortUser();
                            messageWithShortUser.setId(message.getId());
                            messageWithShortUser.setSender(UserMapper.userToUserShortPage(message.getSender()));
                            messageWithShortUser.setRecipient((UserMapper.userToUserShortPage(message.getRecipient())));
                            messageWithShortUser.setText(message.getText());
                            messageWithShortUser.setDateMessage(message.getDateMessage());
                            messageWithShortUser.setWasRead(message.isWasRead());
                            return messageWithShortUser;
                        }).toList();

    }
}
