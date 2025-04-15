package com.duoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/usuarios")
    public ResponseEntity<?> getAllUsers() {
        System.out.println("hola");
        return userService.forwardRequest("https://usercreate-bjh2gkamerf3bua6.eastus2-01.azurewebsites.net/api/usuarios", "GET", null);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        return userService.forwardRequest("https://usercreate-bjh2gkamerf3bua6.eastus2-01.azurewebsites.net/api/usuarios/" + id, "GET", null);
    }

    @PostMapping("/usuarios")
    public ResponseEntity<?> createUser(@RequestBody String body) {
        return userService.forwardRequest("https://userupdate-cvhpgfgjdmatfagq.eastus2-01.azurewebsites.net/api/usuarios", "POST", body);
    }

    @PutMapping("/usuarios")
    public ResponseEntity<?> updateUser(@RequestBody String body) {
        return userService.forwardRequest("https://userupdate-cvhpgfgjdmatfagq.eastus2-01.azurewebsites.net/api/usuarios", "PUT", body);
    }

    @DeleteMapping("/usuarios")
    public ResponseEntity<?> deleteUser(@RequestBody String body) {
        return userService.forwardRequest("https://userupdate-cvhpgfgjdmatfagq.eastus2-01.azurewebsites.net/api/usuarios", "DELETE", body);
    }
}

