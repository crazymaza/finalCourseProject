package com.github.server.controller;

import com.github.server.Atm;
import com.github.server.dao.Card;
import com.github.server.dao.Login;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/atm")
public class AtmController {
    private Atm atm;

    @PostMapping(path = "show", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Card>> getClientProducts(@RequestBody Login clientData) {
        log.info("New request from client with login {}", clientData.getLogin());
        String clientToken = atm.checkClient(clientData.getLogin(), clientData.getPassword()).orElse("123");
        if (clientToken.equals("123")) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(atm.chooseClientProduct(clientData));
    }
}
