package com.github.client.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Card implements Product {
    private final Long number;
    private Long balance;
}
