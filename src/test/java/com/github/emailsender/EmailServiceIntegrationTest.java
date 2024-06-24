package com.github.emailsender;

import com.github.emailsender.entity.message.Message;
import com.github.emailsender.entity.message.MessageStatus;
import com.github.emailsender.entity.user.User;
import com.github.emailsender.exception.MessageException;
import com.github.emailsender.repository.MessageRepository;
import com.github.emailsender.service.impl.EmailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.TestPropertySource;

import java.time.Instant;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(properties = {
        "SMTP_HOST=your_smtp_host",
        "SMTP_PORT=your_smtp_port",
        "SMTP_USERNAME=your_smtp_username",
        "SMTP_PASSWORD=your_smtp_password"
})
public class EmailServiceIntegrationTest {

    @Autowired
    private EmailServiceImpl emailService;

    @MockBean
    private JavaMailSender mailSender;

    @Autowired
    private MessageRepository messageRepository;

    @Test
    void testSendEmailSuccess() {
        User from = new User("John", "john@example.com");
        User to = new User("Jane", "jane@example.com");
        Message message = new Message(from, List.of(to), "Subject", "Content", Instant.now());

        doNothing().when(mailSender).send(any(SimpleMailMessage.class));

        emailService.sendEmail(message);

        assert message.getMessageStatus() == MessageStatus.DELIVERED;
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
        messageRepository.save(message); // збереження повідомлення
    }

    @Test
    void testSendEmailFailure() {
        User from = new User("John", "john@example.com");
        User to = new User("Jane", "jane@example.com");
        Message message = new Message(from, List.of(to), "Subject", "Content", Instant.now());

        doThrow(new RuntimeException("SMTP error")).when(mailSender).send(any(SimpleMailMessage.class));

        try {
            emailService.sendEmail(message);
        } catch (MessageException e) {
            // expected
        }

        assert message.getMessageStatus() == MessageStatus.UNDELIVERED;
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
        messageRepository.save(message);
    }
}
