package com.ernesto.chat.service;

import com.ernesto.chat.dto.UserRequest;
import com.ernesto.chat.exception.NotFoundEntityException;
import com.ernesto.chat.model.User;
import com.ernesto.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }
    public User getById(String id) {
        return userRepository.findById(id)
                .orElseThrow(NotFoundEntityException::new);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(NotFoundEntityException::new);
    }

    public ResponseEntity<?> register(UserRequest dto) {
        if (userRepository.findByUsername(dto.username()).isPresent()
        && userRepository.findByEmail(dto.email()).isPresent())
            return ResponseEntity.badRequest().body("User exists already!");

        User user = new User(dto.username(), dto.email(), dto.password());
        user.setCreateAt(new Date());
        return ResponseEntity.ok(userRepository.saveAndFlush(user));
    }

    public ResponseEntity<?> updateUser(String id, UserRequest dto) {
       User user = getById(id);
       if (dto.email() != null)
           user.setEmail(dto.email());
       if (dto.username() != null)
           user.setUsername(dto.username());
       if (dto.password() != null)
           user.setPassword(dto.password());

       user.setUpdateAt(new Date());

       userRepository.saveAndFlush(user);

       return ResponseEntity.ok("User updated!");
    }

    public ResponseEntity<?> deleteUser(String id) {
        User user = getById(id);
        userRepository.delete(user);
        return ResponseEntity.ok("User deleted!");
    }
}
