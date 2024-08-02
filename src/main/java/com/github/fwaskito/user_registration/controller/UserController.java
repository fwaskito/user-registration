package com.github.fwaskito.user_registration.controller;

import com.github.fwaskito.user_registration.dto.UserDTO;
import com.github.fwaskito.user_registration.repository.UserRepository;
import com.github.fwaskito.user_registration.utils.CustomErrorType;

import java.util.List;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/user")
public class UserController {

    public static final Logger logger = LoggerFactory
            .getLogger(UserController.class);
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> listAllUsers() {
        List<UserDTO> users = userRepository.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<List<UserDTO>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(
            @PathVariable("id") final Long id) {
        UserDTO user = userRepository.findById(id).orElse(null);
        if (user == null) {
            String message = "User with id " + id + " not found.";
            return new ResponseEntity<UserDTO>(
                    new CustomErrorType(message), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(
            @Valid @RequestBody final UserDTO user) {
        logger.info("Creating User: {}", user);
        if (userRepository.findByName(user.getName()) != null) {
            String message = "Unable to create new user. A user with name "
                    + user.getName() + "already exist.";
            logger.error(message);
            return new ResponseEntity<UserDTO>(
                    new CustomErrorType(message), HttpStatus.CONFLICT);
        }
        userRepository.save(user);
        return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable("id") final Long id, @RequestBody UserDTO user) {
        UserDTO currentUser = userRepository.findById(id).orElse(null);
        if (currentUser == null) {
            String message = "Unable to update. User with id "
                    + id + " not found.";
            return new ResponseEntity<UserDTO>(
                    new CustomErrorType(message), HttpStatus.NOT_FOUND);
        }
        currentUser.setName(user.getName());
        currentUser.setAddress(user.getAddress());
        currentUser.setEmail(user.getEmail());
        userRepository.saveAndFlush(currentUser);
        return new ResponseEntity<UserDTO>(currentUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(
            @PathVariable("id") final Long id) {
        UserDTO user = userRepository.findById(id).orElse(null);
        if (user == null) {
            String message = "Unable to delete. User with id"
                    + id + " not found.";
            return new ResponseEntity<UserDTO>(
                    new CustomErrorType(message), HttpStatus.NO_CONTENT);
        }
        userRepository.deleteById(id);
        return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
    }
}
