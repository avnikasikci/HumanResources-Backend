package com.example.humanresources.api.controllers;


import com.example.humanresources.business.abstracts.UserService;
import com.example.humanresources.core.entities.User;
import com.example.humanresources.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/api/adminuser")
@CrossOrigin
public class AdminUserController {
    private UserService userService;

    @Autowired
    public AdminUserController(UserService userService) {
        super();
        this.userService = userService;
    }
    @GetMapping("/getall")
    public DataResult<List<User>> getAll(){
        return this.userService.getAll();
    }
}
