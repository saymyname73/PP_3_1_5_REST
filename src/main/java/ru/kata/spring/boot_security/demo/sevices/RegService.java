package ru.kata.spring.boot_security.demo.sevices;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import java.util.HashSet;
import java.util.Set;

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
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1));
//        roles.add(new Role(2));
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void update(int id, User user) {
        User u = userRepository.getById(id);
        u.setName(user.getName());
        u.setSurname(user.getSurname());
        u.setAge(user.getAge());
        u.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(u);
    }
}
