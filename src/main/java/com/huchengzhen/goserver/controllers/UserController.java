package com.huchengzhen.goserver.controllers;

import com.huchengzhen.goserver.model.User;
import com.huchengzhen.goserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public User findUserById(@RequestParam int id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public void register(@RequestBody User user) {
        userService.register(user);
    }
}
