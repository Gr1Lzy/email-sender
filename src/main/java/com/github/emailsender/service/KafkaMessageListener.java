package com.github.emailsender.service;

import com.github.emailsender.entity.message.Message;
import com.github.emailsender.repository.MessageRepository;
import com.github.emailsender.service.impl.EmailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaMessageListener {

    private final EmailServiceImpl emailService;
    private final MessageRepository messageRepository;

    @KafkaListener(topics = "email-topic", groupId = "email-group")
    public void listen(Message message) {
        messageRepository.save(message);
        emailService.sendEmail(message);
    }
}
