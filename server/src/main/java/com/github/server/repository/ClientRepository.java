package com.github.server.repository;

import com.github.server.dao.Client;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    Client findClientByLogin(String login);
    Client findClientByToken(String token);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
    value = "update Client set token = :newToken where login = :login")
    void setClientToken(@Param("login") String login, @Param("newToken") String newToken);
}
