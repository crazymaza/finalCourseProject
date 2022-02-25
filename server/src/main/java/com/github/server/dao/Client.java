package com.github.server.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.server.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Table
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue
    private Long id;

    @JsonManagedReference
    @OneToMany(targetEntity = Card.class,
            mappedBy = "client",
            cascade = CascadeType.ALL
    )
    private Set<Product> products;
    private String login;
    private int password;
    private String token;
}
