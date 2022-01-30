package com.github.client.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.client.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card implements Product {
    @Id
    @GeneratedValue
    private long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Client clientId;
    private long balance;
    private long number;
    private String title;
}
