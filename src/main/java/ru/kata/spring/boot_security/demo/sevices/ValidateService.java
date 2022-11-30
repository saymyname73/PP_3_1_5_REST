package ru.kata.spring.boot_security.demo.sevices;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.Optional;

@Service
public class ValidateService {

    private final UserRepository userRepository;

    public ValidateService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> userOptional(String name) {
        return userRepository.findByName(name);
    }
}
