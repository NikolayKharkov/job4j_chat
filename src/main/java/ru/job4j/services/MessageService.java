package ru.job4j.services;

import org.springframework.stereotype.Service;
import ru.job4j.models.Message;
import ru.job4j.repositories.MessageRepository;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message saveOrUpdate(Message message) {
        return messageRepository.save(message);
    }

}
