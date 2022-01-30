package com.github.service;

import com.github.client.Client;
import com.github.client.product.Card;
import com.github.client.product.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class AtmTest {

    @Test
    public void getProductBalanceCorrectly() {
        Card card = new Card("Debt card", 123L, 123L);
        Set<Product> productSet = new HashSet<>();
        productSet.add(card);
        Client client = new Client(productSet, 1234);
        Atm atm = new Atm(client);
        Assertions.assertEquals(123L, atm.getProductBalance(card));
    }

    @Test
    public void getProductBalanceWithException() {
        Client client = new Client(new HashSet<>(), 1234);
        Atm atm = new Atm(client);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            atm.getProductBalance(null);
        });
    }

    @Test
    public void testWithoutFramework() throws IOException {
        String userInput = String.format("1234%s1",
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
        BufferedReader bufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("1234").thenReturn("1");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        Card card = new Card("Debt card", 123L, 123L);
        Set<Product> productSet = new HashSet<>();
        productSet.add(card);
        Client client = new Client(productSet, 1234);
        Atm atm = spy(new Atm(client));
        atm.runSession();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];

        Assertions.assertEquals("Balance is 123", actual);
    }

    @Test
    @StdIo({"1234", "1"})
    public void testWithJunitPioneer(StdOut out) {
        Card card = new Card("Debt card", 123L, 123L);
        Set<Product> productSet = new HashSet<>();
        productSet.add(card);
        Client client = new Client(productSet, 1234);
        Atm atm = new Atm(client);
        atm.runSession();
        String actual = out.capturedLines()[out.capturedLines().length - 1];
        Assertions.assertEquals("Balance is 123", actual);
    }
}
