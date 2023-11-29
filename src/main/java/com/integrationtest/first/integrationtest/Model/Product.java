package com.integrationtest.first.integrationtest.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Product {

    @Id
    /*@SequenceGenerator(name = "product_seq",
    sequenceName = "product_seq",
    allocationSize = 1)*/
    @GeneratedValue(strategy =GenerationType.AUTO) //GenerationType.SEQUENCE generator = "product_seq")
    private int id;
    private String name;
    private int quantity;
    private double price;

    public Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}
