package com.duoc;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<?> forwardRequest(String url, String method, String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response;
        if (method.equalsIgnoreCase("POST")) {
            response = restTemplate.postForEntity(url, entity, String.class);
        } else if (method.equalsIgnoreCase("PUT")) {
            response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
        } else {
            return ResponseEntity.badRequest().body("Unsupported method: " + method);
        }

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}

