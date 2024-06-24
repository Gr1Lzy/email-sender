package com.github.emailsender.repository;

import com.github.emailsender.entity.message.Message;
import com.github.emailsender.entity.message.MessageStatus;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends ElasticsearchRepository<Message, String> {
    List<Message> findAllByMessageStatus(MessageStatus messageStatus);
}



