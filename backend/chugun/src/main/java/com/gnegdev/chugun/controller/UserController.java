package com.gnegdev.chugun.controller;

import com.gnegdev.chugun.entity.User;
import com.gnegdev.chugun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User response = userService.createUser(user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<>(ex, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get/{party_rk}")
    public ResponseEntity<?> getUser(@PathVariable("party_rk") Integer partyRk) {
        Optional<User> response = userService.findByPartyRk(partyRk);
        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);

    }
}
