package org.udemy.sergey.user.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.udemy.sergey.user.model.User;
import org.udemy.sergey.user.model.UserEntity;
import org.udemy.sergey.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;
    @GetMapping
    public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page
            , @RequestParam(value = "limit", required = false) String limit) {
        return "retrieve User from page: " + page + " and limit: " + limit;
    }
    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserEntity> getUserId(@PathVariable(value = "userId") String userId) {
        UserEntity userEntity = userService.getUser(userId);
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserEntity userEntity) {
        User result = userService.createUser(userEntity);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping(path="/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable(value="userId") String userId, @Valid @RequestBody UserEntity userEntity) {
        User user = userService.updateUser(userId, userEntity);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(path="/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value="userId") String userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
