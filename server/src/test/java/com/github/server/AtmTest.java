package com.github.server;

import com.github.server.dao.Card;
import com.github.server.dao.Client;
import com.github.server.dao.Login;
import com.github.server.repository.CardRepository;
import com.github.server.repository.ClientRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AtmTest {
    @MockBean
    private Client clientMock;
    @MockBean
    private ClientRepository clientRepositoryMock;
    @MockBean
    private CardRepository cardRepositoryMock;
    @Autowired
    private Atm atm;

    @Test
    public void getProductBalanceCorrectly() {
        Card card = new Card(1L, clientMock, 123L, 123L, "Debt card");
        Assertions.assertEquals(123L, atm.getProductBalance(card));
    }

    @Test
    public void getProductBalanceWithException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            atm.getProductBalance(null);
        });
    }

    @Test
    public void returnOptionalEmptyWhenClientNotFound() {
        Mockito.when(clientRepositoryMock.findClientByLogin("123")).thenReturn(null);
        Assertions.assertEquals(Optional.empty(), atm.checkClient("123", 123));
    }

    @Test
    public void getTokenForClient() {
        byte[] encodedAuth = Base64.encodeBase64("123:123".getBytes(StandardCharsets.US_ASCII));
        String testToken = new String(encodedAuth);
        Mockito.when(clientRepositoryMock.findClientByLogin("123")).thenReturn(
                Client.builder()
                        .login("123")
                        .password(123)
                        .build()
        );
        Assertions.assertEquals(Optional.of(testToken), atm.checkClient("123", 123));
    }

    @Test
    public void getClientProducts() {
        Set<Card> testCardSet = new HashSet<>();
        testCardSet.add(new Card(1L, clientMock, 123L, 123L, "Debt card1"));
        testCardSet.add(new Card(2L, clientMock, 1234L, 1234L, "Debt card2"));
        testCardSet.add(new Card(3L, clientMock, 12345L, 12345L, "Debt card3"));

        Mockito.when(cardRepositoryMock.getAllByClientId("123", 123))
                .thenReturn(testCardSet);

        Assertions.assertIterableEquals(testCardSet,
                atm.chooseClientProduct(Login
                        .builder()
                        .login("123")
                        .password(123)
                        .build()));
    }
}
