package com.example.portfolio.model;

import jakarta.persistence.*;

@Entity
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double amount;

    public Investment() {}

    public Investment(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public double getAmount() { return amount; }

    public void setName(String name) { this.name = name; }
    public void setAmount(double amount) { this.amount = amount; }
}