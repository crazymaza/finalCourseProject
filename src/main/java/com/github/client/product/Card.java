package com.github.client.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Card implements Product {
    private final String title;
    private final Long number;
    private Long balance;
}
