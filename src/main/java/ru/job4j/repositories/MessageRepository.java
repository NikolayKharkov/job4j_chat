package ru.job4j.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.models.Message;

public interface MessageRepository extends CrudRepository<Message, Integer> {
}
