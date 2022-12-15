package ru.kata.spring.boot_security.demo.sevices;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

@Service
public class ValidateService {

    private final UserRepository userRepository;

    public ValidateService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User userOptional(String email) {
        return userRepository.findUserByEmail(email);

    }
}
