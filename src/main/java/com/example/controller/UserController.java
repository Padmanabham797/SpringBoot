package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
    @PostMapping("/create")
    public String createUser(@RequestBody User user){
        return userService.createUser(user);
    }
    @PutMapping("/{id}")
    public String updateUser(@RequestBody User user,@PathVariable Long id){
        return userService.updateUser(user,id);
    }
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
