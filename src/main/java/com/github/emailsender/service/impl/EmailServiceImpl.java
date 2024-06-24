package com.github.emailsender.service.impl;

import com.github.emailsender.entity.message.Message;
import com.github.emailsender.entity.message.MessageStatus;
import com.github.emailsender.entity.user.User;
import com.github.emailsender.exception.MessageException;
import com.github.emailsender.repository.MessageRepository;
import com.github.emailsender.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final MessageRepository messageRepository;

    @Transactional
    public void sendEmail(Message message) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(message.getFrom().getEmail());
            mailMessage.setTo(message.getTo().stream().map(User::getEmail).toArray(String[]::new));
            mailMessage.setSubject(message.getSubject());
            mailMessage.setText(message.getContent());
            mailSender.send(mailMessage);
            message.setMessageStatus(MessageStatus.DELIVERED);
        } catch (Exception e) {
            message.setMessageStatus(MessageStatus.UNDELIVERED);
            throw new MessageException("Failed to send email: " + e.getMessage(), e);
        } finally {
            messageRepository.save(message);
        }
    }
}
