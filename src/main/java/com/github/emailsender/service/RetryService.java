package com.github.emailsender.service;

import com.github.emailsender.entity.message.Message;
import com.github.emailsender.entity.message.MessageStatus;
import com.github.emailsender.repository.MessageRepository;
import com.github.emailsender.service.impl.EmailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RetryService {

    private final MessageRepository messageRepository;
    private final EmailServiceImpl emailService;

    @Scheduled(fixedRate = 300000)
    public void retryFailedMessages() {
        List<Message> failedMessages = messageRepository.findAllByMessageStatus(MessageStatus.UNDELIVERED);
        failedMessages.forEach(message -> {
            try {
                emailService.sendEmail(message);
            } catch (Exception e) {
                messageRepository.save(message);
            }
        });
    }
}
