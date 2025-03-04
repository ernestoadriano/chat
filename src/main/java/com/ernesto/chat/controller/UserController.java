package com.ernesto.chat.controller;

import com.ernesto.chat.dto.UserRequest;
import com.ernesto.chat.model.User;
import com.ernesto.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;
 
    @GetMapping("/user/get")
    public List<User> getAll() {
        return userService.getAll();
    }
    @GetMapping("/user/id/{id}")
    public User getById(@PathVariable(value = "id") String id) {
        return userService.getById(id);
    }

    @GetMapping("/user/username/{username}")
    public User getByUsername(@PathVariable(value = "username") String username) {
        return userService.getByUsername(username);
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> register(@RequestBody UserRequest dto) {
        return userService.register(dto);
    }

    @PutMapping("/user/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(value = "id") String id,
                                        @RequestBody UserRequest dto) {
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") String id) {
        return userService.deleteUser(id);
    }
}
