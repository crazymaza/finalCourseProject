package com.github.service;

import com.github.client.Client;
import com.github.client.product.Product;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
public class Atm implements MoneyService {
    private final Client client;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void runSession() {
        if (checkClient()) {
            Product chosenProduct = chooseClientProduct();
            showProductBalance(chosenProduct);
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long getProductBalance(Product product) {
        if (product == null) throw new IllegalArgumentException("Product can't be null");
        return product.getBalance();
    }

    private boolean checkClient() {
        System.out.println("Введите пароль:");
        try {
            int attemptCount = 3;
            do {
                String pin = br.readLine();
                if (Long.parseLong(pin) == client.getPassword()) {
                    return true;
                } else {
                    attemptCount--;
                    System.out.println("Wrong password, try again. You have " + attemptCount + " attempt.");
                }
            } while (attemptCount != 0);
            br.close();
            System.out.println("You account has blocked. Call to bank. Bye!");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Product chooseClientProduct() {
        Map<Integer, Product> productMap = new HashMap<>();
        System.out.println("Выберите продукт по которому хотите узнать баланс:");

        AtomicInteger count = new AtomicInteger(1);
        client.getProducts().forEach(item -> {
            productMap.put(count.getAndIncrement(), item);
        });

        productMap.forEach((key, value) -> {
            String productNumToString = String.valueOf(value.getNumber());
            String productShortNumber = productNumToString.substring(productNumToString.length() - 3);
            System.out.printf("%s. %s\n", key, value.getTitle() + " " + productShortNumber);
        });
        try {
            return productMap.get(Integer.parseInt(br.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void showProductBalance(Product product) {
        System.out.println("Balance is " + getProductBalance(product));
    }
}
