package com.github.emailsender.dto;

import com.github.emailsender.entity.message.MessageStatus;
import com.github.emailsender.entity.user.User;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class MessageDto {
    private User from;
    private List<User> to;
    private String subject;
    private String content;
    private MessageStatus messageStatus;
    private Instant instant;
}
