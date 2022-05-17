package ru.job4j.services;

import ru.job4j.models.User;
import org.springframework.stereotype.Service;
import ru.job4j.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    public User saveOrUpdate(User user) {
        return userRepository.save(user);
    }
}
