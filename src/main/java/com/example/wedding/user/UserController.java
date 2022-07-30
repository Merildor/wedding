package com.example.wedding.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserDao userRepository;

    public UserController(UserDao userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        list.addAll(userRepository.findAll());
        return list;
    }

    @GetMapping(path = "{id}")
    public User getUser(@PathVariable("id") Long id){
        return getUsers().stream().filter(user -> id.equals(user.getId())).findFirst().orElseThrow(() -> new IllegalStateException("User " + id + "does not exists"));
    }
}
