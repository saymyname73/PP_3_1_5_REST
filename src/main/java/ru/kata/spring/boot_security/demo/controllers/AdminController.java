package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.sevices.RegService;
import ru.kata.spring.boot_security.demo.sevices.UserDetailsServiceImp;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserDetailsServiceImp userDetailsServiceImp;
    private final RegService regService;


    public AdminController(UserDetailsServiceImp userDetailsServiceImp, RegService regService) {
        this.userDetailsServiceImp = userDetailsServiceImp;
        this.regService = regService;
    }

    @GetMapping()
    public String index(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("users", userDetailsServiceImp.listUsers());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user1 = (User) authentication.getPrincipal();
        model.addAttribute("user1", user1);
        model.addAttribute("roles", user1.getRoles());
        return "/admin/admin";
    }

    @GetMapping("/info")
    public String info(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user1 = (User) authentication.getPrincipal();
        model.addAttribute("user", user1);
        return "/admin/info";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("user") User user) {
        regService.register(user);
        return "redirect:/admin";
    }

    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("user") User user,@PathVariable("id") int id) {
        regService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userDetailsServiceImp.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/getOne")
    @ResponseBody
    public User getUserById(int id) {
        return userDetailsServiceImp.show(id);
    }
}
