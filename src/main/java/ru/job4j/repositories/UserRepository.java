package ru.job4j.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
