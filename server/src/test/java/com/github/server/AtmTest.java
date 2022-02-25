//package com.github.server;
//
//import com.github.server.dao.Card;
//import com.github.server.dao.Client;
//import com.github.server.repository.CardRepository;
//import com.github.server.repository.ClientRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junitpioneer.jupiter.StdIo;
//import org.junitpioneer.jupiter.StdOut;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.PrintStream;
//import java.util.HashSet;
//import java.util.Set;
//
//@ExtendWith(SpringExtension.class)
//public class AtmTest {
//    @MockBean
//    private Client client;
//    @MockBean
//    private ClientRepository clientRepository;
//    @MockBean
//    private CardRepository cardRepository;
//
//    private final Atm atm = new Atm(client, clientRepository);
//
//    @Test
//    public void getProductBalanceCorrectly() {
//        Card card = new Card(1L, client, 123L, 123L, "Debt card");
//        Assertions.assertEquals(123L, atm.getProductBalance(card));
//    }
//
//    @Test
//    public void getProductBalanceWithException() {
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            atm.getProductBalance(null);
//        });
//    }
//
//    @Test
//    public void testWithoutFramework() throws IOException {
//        String userInput = String.format("1234%s1",
//                System.lineSeparator());
//        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
//        System.setIn(bais);
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        PrintStream printStream = new PrintStream(baos);
//        System.setOut(printStream);
//
//        Card card = new Card(1L, client, 123L, 123L, "Debt card");
//        Set<Product> cardSet = new HashSet<>();
//        cardSet.add(card);
//        Atm atm = new Atm(Client
//                .builder()
//                .products(cardSet)
//                .password(1234)
//                .build(),
//                clientRepository);
//        atm.runSession();
//
//        String[] lines = baos.toString().split(System.lineSeparator());
//        String actual = lines[lines.length - 1];
//
//        Assertions.assertEquals("Balance is 123", actual);
//    }
//
//    @Test
//    @StdIo({"1234", "1"})
//    public void testWithJunitPioneer(StdOut out) {
////        Card card = new Card(1L, client, 123L, 123L, "Debt card");
////        Set<Product> cardSet = new HashSet<>();
////        cardSet.add(card);
////        Client client = new Client(1L, cardSet, "login", 1234);
//        Atm atm = new Atm(client, clientRepository);
//        atm.runSession();
//        String actual = out.capturedLines()[out.capturedLines().length - 1];
//        Assertions.assertEquals("Balance is 123", actual);
//    }
//}
