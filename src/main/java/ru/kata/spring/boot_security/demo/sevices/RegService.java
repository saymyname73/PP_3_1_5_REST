package ru.kata.spring.boot_security.demo.sevices;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

@Service
public class RegService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void update(int id, User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User u = userRepository.getById(id);
        u.setName(user.getName());
        u.setSurname(user.getSurname());
        u.setAge(user.getAge());
        u.setEmail(user.getEmail());
        u.setRoles(user.getRoles());
        u.setPassword(user.getPassword());
        userRepository.save(u);
    }
}
