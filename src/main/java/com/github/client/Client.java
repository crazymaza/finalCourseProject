package com.github.client;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.client.product.Card;
import com.github.client.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue
    private long id;

    @JsonManagedReference
    @OneToMany(targetEntity = Card.class,
            mappedBy = "clientId",
            cascade = CascadeType.ALL
    )
    private Set<Product> products;
    private int password;
}
