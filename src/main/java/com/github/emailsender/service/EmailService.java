package com.github.emailsender.service;

import com.github.emailsender.entity.message.Message;

public interface EmailService {
    void sendEmail(Message message);
}
