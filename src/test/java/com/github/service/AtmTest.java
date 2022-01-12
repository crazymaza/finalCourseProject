package com.github.service;

import com.github.client.product.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AtmTest {

    @Test
    public void getProductBalanceCorrectly() {
        Card card = new Card(123L, 123L);
        Atm atm = new Atm();
        Assertions.assertEquals(123L, atm.getProductBalance(card));
    }
}
