package com.github.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Set;

@Slf4j
@Controller
public class WebController {
    @PostMapping("/show")
    public String show(@RequestParam String username, @RequestParam String password, Model model) {
        RestTemplate restTemplate = new RestTemplate();

        Login loginBody = Login
                .builder()
                .login(username)
                .password(password)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Login> requestEntity = new HttpEntity<>(loginBody, headers);

        String URL = "http://localhost:8080/atm/show";

        Set<Card> cardSet = restTemplate.postForEntity(URL, requestEntity,
                Set.class, Card.class).getBody();

        model.addAttribute("cards", cardSet);
        return "show";
    }
}
