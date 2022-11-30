package ru.kata.spring.boot_security.demo.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.sevices.ValidateService;

import java.util.Optional;

@Component
public class UserValidator implements Validator {

    private final ValidateService validateService;

    public UserValidator(ValidateService validateService) {
        this.validateService = validateService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        Optional<User> optionalUser = validateService.userOptional(user.getUsername());

        if (optionalUser.isPresent()) {
            errors.rejectValue("name", "", "User с таким именем уже зарегистрирован");
        }
    }
}
