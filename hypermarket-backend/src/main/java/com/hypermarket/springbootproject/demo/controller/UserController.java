package com.hypermarket.springbootproject.demo.controller;


import com.hypermarket.springbootproject.demo.dto.UserDtoResponse;
import com.hypermarket.springbootproject.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(path = "/{username}")
    public UserDtoResponse getUser(@PathVariable("username") String username) {
        return userService.getUser(username);
    }

    @PostMapping(path = "/register")
    public UserDtoResponse registerUser(@Valid @RequestBody UserDtoResponse userDto) {
        return userService.registerUser(userDto);
    }

    @PutMapping(path = "/{username}")
    public UserDtoResponse updateUser(@PathVariable("username") String username,
                                      @Valid @RequestBody UserDtoResponse userDto) {
        return userService.updateUser(username, userDto);
    }

    @DeleteMapping(path = "/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }
}
