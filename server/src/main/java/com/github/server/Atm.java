package com.github.server;

import com.github.server.dao.Card;
import com.github.server.dao.Client;
import com.github.server.dao.Login;
import com.github.server.repository.CardRepository;
import com.github.server.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class Atm implements MoneyService {
    private final ClientRepository clientRepository;
    private final CardRepository cardRepository;
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public long getProductBalance(Product product) {
        if (product == null) throw new IllegalArgumentException("Product can't be null");
        return product.getBalance();
    }

    public Optional<String> checkClient(String login, int password) {
        Optional<Client> client = Optional.ofNullable(clientRepository.findClientByLogin(login));
        if (password == client.orElseGet(Client::new).getPassword()) {
            log.info("Client with login {} found in database.", login);
            String authData = login + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(authData.getBytes(StandardCharsets.US_ASCII));
            String token = new String(encodedAuth);
            clientRepository.setClientToken(login, token);
            return Optional.of(token);
        }
        log.info("Client with login {} not found in database", login);
        return Optional.empty();
    }

    public Set<Card> chooseClientProduct(Login client) {
        return cardRepository.getAllByClientId(client.getLogin(), client.getPassword());
    }
}
