package com.example.wedding.controllers;

import com.example.wedding.dao.UserDao;
import com.example.wedding.user.User;
import com.example.wedding.user.UserWithLesserDetails;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("management/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserDao userDao;

    @GetMapping("/withLessDetails")
    public List<UserWithLesserDetails> getUsersWithLesserDetails() {

        return userDao.findAllUsersWithLessDetails();
    }

    @GetMapping(path = "/withLessDetails/{id}")
    public UserWithLesserDetails getUserWithLessDetails(@PathVariable("id") Long id){

        return getUsersWithLesserDetails().stream().filter(user -> id.equals(user.getId())).findFirst()
                .orElseThrow(() -> new IllegalStateException("User with ID: " + id + " does not exists!"));
    }

    @GetMapping("/withAllDetails")
    public List<User> getUsersWithAllDetails() {

        return userDao.findAll();
    }

    @GetMapping(path = "/withAllDetails/{id}")
    public User getUserWithAllDetails(@PathVariable("id") Long id){

        return getUsersWithAllDetails().stream().filter(user -> id.equals(user.getId())).findFirst()
                .orElseThrow(() -> new IllegalStateException("User with ID: " + id + " does not exists!"));
    }

}
