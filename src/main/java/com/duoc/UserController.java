package com.duoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventGridService eventGridService;

    @GetMapping("/usuarios")
    public ResponseEntity<?> getAllUsers() {
        String query = "query { getAllUsers { id name username password rol } }";
        return userService.forwardGraphQLRequest(query);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        String query = String.format("{ getUserById(id: %s) { id name username password rol } }", id);
        return userService.forwardGraphQLRequest(query);
    }

    @PostMapping("/usuarios")
    public ResponseEntity<?> createUser(@RequestBody String body) {
        try {
            eventGridService.sendUserEvent(body, "users", "create");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/usuarios")
    public ResponseEntity<?> updateUser(@RequestBody String body) {
        try {
            eventGridService.sendUserEvent(body, "users", "update");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/usuarios")
    public ResponseEntity<?> deleteUser(@RequestBody String body) {
        try {
            eventGridService.sendUserEvent(body, "users", "delete");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}

