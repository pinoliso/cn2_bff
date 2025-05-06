package com.duoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RolController {

    @Autowired
    private RolService RolService;

    @Autowired
    private EventGridService eventGridService;

    @GetMapping("/roles")
    public ResponseEntity<?> getAllRoles() {
        String query = "query { getAllRoles { id name } }";
        return RolService.forwardGraphQLRequest(query);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable String id) {
        String query = String.format("{ getRolById(id: %s) { id name } }", id);
        return RolService.forwardGraphQLRequest(query);
    }

    @PostMapping("/roles")
    public ResponseEntity<?> createRole(@RequestBody String body) {
        try {
            eventGridService.sendRolEvent(body, "rol", "create");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/roles")
    public ResponseEntity<?> updateRole(@RequestBody String body) {
        try {
            eventGridService.sendRolEvent(body, "rol", "update");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/roles")
    public ResponseEntity<?> deleteRole(@RequestBody String body) {
        try {
            eventGridService.sendRolEvent(body, "rol", "delete");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}

