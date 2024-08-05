package com.kardoaward.message.repository;

import com.kardoaward.message.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m " +
            "FROM Message m " +
            "WHERE m.sender.id = ?1 " +
            "AND m.recipient.id = ?2 " +
            "OR m.sender.id = ?2 " +
            "AND m.recipient.id = ?1 " +
            "ORDER BY m.dateMessage asc")
    List<Message> findAllBySenderIdAndRecipientId(Long senderId, Long recipientId);
}
