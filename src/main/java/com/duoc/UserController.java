package com.duoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user_create")
    public ResponseEntity<?> createUser(@RequestBody String body) {
        return userService.forwardRequest("https://usercreate-bjh2gkamerf3bua6.eastus2-01.azurewebsites.net/api/user_create", "POST", body);
    }

    @PostMapping("/user_update")
    public ResponseEntity<?> updateUser(@RequestBody String body) {
        return userService.forwardRequest("https://userupdate-cvhpgfgjdmatfagq.eastus2-01.azurewebsites.net/api/user_update", "POST", body);
    }
}

