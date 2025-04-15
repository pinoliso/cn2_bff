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

        switch (method) {
            case "POST":
                response = restTemplate.postForEntity(url, entity, String.class);
                break;
            case "PUT":
                response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
                break;
            case "DELETE":
                response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
                break;
            case "GET":
                response = restTemplate.getForEntity(url, String.class);
                break;
            default:
                return ResponseEntity.badRequest().body("Unsupported method: " + method);
        }

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    public ResponseEntity<?> forwardGraphQLRequest(String query) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "{\"query\":\"" + query + "\"}";

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("https://graphqueries.azurewebsites.net/api/roles", entity, String.class);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}

