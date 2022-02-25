package com.github.server.repository;

import com.github.server.Product;
import com.github.server.dao.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.server.dao.Card;

import java.util.Set;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

    @Query(nativeQuery = true,
    value = "select ca.* from Card ca " +
            "left join Client cl " +
            "on ca.client_id = cl.id " +
            "where cl.login = :login and cl.password = :password " +
            "order by ca.id")
    Set<Card> getAllByClientId(@Param("login") String login, @Param("password") int password);
}
