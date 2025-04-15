package com.duoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RolController {

    @Autowired
    private UserService userService;

    @GetMapping("/roles")
    public ResponseEntity<?> getAllRoles() {
        String query = "query { getAllRoles { id name } }";
        return userService.forwardGraphQLRequest(query);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable String id) {
        String query = String.format("{ getRolById(id: %s) { id name } }", id);
        return userService.forwardGraphQLRequest(query);
    }

    @PostMapping("/roles")
    public ResponseEntity<?> createRole(@RequestBody String body) {
        String mutation = String.format("mutation { createRole(input: %s) { id name } }", body);
        return userService.forwardGraphQLRequest(mutation);
    }

    @PutMapping("/roles")
    public ResponseEntity<?> updateRole(@RequestBody String body) {
        String mutation = String.format("mutation { updateRole(input: %s) { id name } }", body);
        return userService.forwardGraphQLRequest(mutation);
    }

    @DeleteMapping("/roles")
    public ResponseEntity<?> deleteRole(@RequestBody String body) {
        String mutation = String.format("mutation { deleteRole(input: %s) { id } }", body);
        return userService.forwardGraphQLRequest(mutation);
    }
}

