package com.kardoaward.message.model;

import com.kardoaward.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id")
    private User recipient;
    private String text;
    @Column(name = "date_message")
    private LocalDateTime dateMessage;
    @Column(name = "was_read")
    private boolean wasRead;

}
