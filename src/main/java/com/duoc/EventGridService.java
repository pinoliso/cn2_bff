package com.duoc;

import com.google.gson.Gson;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class EventGridService {

    private static final String USER_TOPIC_ENDPOINT = "https://user-grid.eastus2-1.eventgrid.azure.net/api/events";
    private static final String USER_ACCESS_KEY = "E3dlE0UvaIMwsNXz7kvoJYbFPURsKSdpw8OzXJVQ0vTra3KsJYXrJQQJ99BEACHYHv6XJ3w3AAABAZEGoUEv";
    private static final String ROL_TOPIC_ENDPOINT = "https://rol-grid.eastus2-1.eventgrid.azure.net/api/events";
    private static final String ROL_ACCESS_KEY = "6dWLcKP9xE1MeZMYAYzZ5pfjtMaaACq9RJR0YQ97aFi8SukNBst3JQQJ99BEACHYHv6XJ3w3AAABAZEGb5Az";
    private final RestTemplate restTemplate;
    private final Gson gson;

    public EventGridService() {
        this.restTemplate = new RestTemplate();
        this.gson = new Gson();
    }

    public void sendUserEvent(String message, String domain, String action) {

        Map<String, Object> event = new HashMap<>();
        event.put("id", UUID.randomUUID().toString());
        event.put("eventType", domain + "." + action);
        event.put("subject", action);
        event.put("eventTime", new Date());
        event.put("data", message);
        event.put("dataVersion", "1.0");

        // Event Grid espera una lista aunque sea solo un evento
        List<Map<String, Object>> events = List.of(event);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("aeg-sas-key", USER_ACCESS_KEY);

        HttpEntity<String> request = new HttpEntity<>(gson.toJson(events), headers);
        ResponseEntity<String> response = restTemplate.postForEntity(USER_TOPIC_ENDPOINT, request, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Evento enviado exitosamente");
        } else {
            System.out.println("Error al enviar el evento: " + response.getStatusCode());
        }
    }

    public void sendRolEvent(String message, String domain, String action) {

        Map<String, Object> event = new HashMap<>();
        event.put("id", UUID.randomUUID().toString());
        event.put("eventType", domain + "." + action);
        event.put("subject", action);
        event.put("eventTime", new Date());
        event.put("data", message);
        event.put("dataVersion", "1.0");

        // Event Grid espera una lista aunque sea solo un evento
        List<Map<String, Object>> events = List.of(event);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("aeg-sas-key", ROL_ACCESS_KEY);

        HttpEntity<String> request = new HttpEntity<>(gson.toJson(events), headers);
        ResponseEntity<String> response = restTemplate.postForEntity(ROL_TOPIC_ENDPOINT, request, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Evento enviado exitosamente");
        } else {
            System.out.println("Error al enviar el evento: " + response.getStatusCode());
        }
    }
}

