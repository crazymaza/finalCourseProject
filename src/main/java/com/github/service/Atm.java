package com.github.service;

import com.github.client.product.Product;

public class Atm implements MoneyService {
    @Override
    public Long getProductBalance(Product product) {
        return product.getBalance();
    }
}
