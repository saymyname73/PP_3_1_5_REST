package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.sevices.RegService;
import ru.kata.spring.boot_security.demo.util.UserValidator;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserValidator userValidator;

    private final RegService regService;

    public AuthController(UserValidator userValidator, RegService regService) {
        this.userValidator = userValidator;
        this.regService = regService;
    }

    @GetMapping("/reg")
    public String regPage(@ModelAttribute("user") User user) {
        return "/reg";
    }

    @PostMapping("/reg")
    public String performReg(@ModelAttribute("user") User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/reg";
        }
        regService.register(user);

        return "redirect:/";
    }
}
