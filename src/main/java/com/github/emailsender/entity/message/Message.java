package com.github.emailsender.entity.message;

import com.github.emailsender.entity.user.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Document(indexName = "messages")
public class Message {
    @Id
    private String id;
    private final User from;
    private final List<User> to;
    private final String subject;
    private final String content;
    private MessageStatus messageStatus;
    private final Instant instant;
}
