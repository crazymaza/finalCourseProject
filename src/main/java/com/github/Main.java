package com.github;

import com.github.client.Client;
import com.github.client.product.Card;
import com.github.client.product.Product;
import com.github.service.Atm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
//        SpringApplication.run(Main.class, args);
        Card card = new Card("Debt card", 123L, 123L);
        Card card1 = new Card("Debt card1", 123456L, 1234L);
        Card card2 = new Card("Debt card2", 123456789L, 12345L);
        Set<Product> productSet = new HashSet<>();
        productSet.add(card);
        productSet.add(card1);
        productSet.add(card2);
        Client client = new Client(productSet, 123);
        Atm atm = new Atm(client);
        atm.runSession();
    }
}
