package com.github.client;

import com.github.client.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class Client {
    private Set<Product> productSet;
}
