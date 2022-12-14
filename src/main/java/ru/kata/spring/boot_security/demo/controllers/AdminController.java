package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.sevices.RegService;
import ru.kata.spring.boot_security.demo.sevices.UserDetailsServiceImp;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserDetailsServiceImp userDetailsServiceImp;
    private final RegService regService;

    public AdminController(UserDetailsServiceImp userDetailsServiceImp, RegService regService) {
        this.userDetailsServiceImp = userDetailsServiceImp;
        this.regService = regService;
    }

    @GetMapping("/info")
    public User info() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return userDetailsServiceImp.show(user.getId());
    }

    @GetMapping("/users")
    public List<User> allUsers() {
        return userDetailsServiceImp.listUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable ("id") int id) {
        return userDetailsServiceImp.show(id);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody User user) {
        regService.register(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody User user, @PathVariable("id") int id) {
        regService.update(id, user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        userDetailsServiceImp.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
