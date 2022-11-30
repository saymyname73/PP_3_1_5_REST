package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.sevices.RegService;
import ru.kata.spring.boot_security.demo.sevices.UserDetailsServiceImp;
import ru.kata.spring.boot_security.demo.util.UserValidator;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserDetailsServiceImp userDetailsServiceImp;
    private final UserValidator userValidator;
    private final RegService regService;


    public AdminController(UserDetailsServiceImp userDetailsServiceImp, UserValidator userValidator, RegService regService) {
        this.userDetailsServiceImp = userDetailsServiceImp;
        this.userValidator = userValidator;
        this.regService = regService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userDetailsServiceImp.listUsers());
        return "/admin/index";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "admin/new";
    }

    @PatchMapping()
    public String create(@ModelAttribute("user") User user, BindingResult bindingResult) {

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "admin/new";
        }
        regService.register(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDetailsServiceImp.show(id));
        return "admin/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDetailsServiceImp.show(id));
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") int id, BindingResult bindingResult) {

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "admin/edit";
        }
        regService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userDetailsServiceImp.delete(id);
        return "redirect:/admin";
    }
}
