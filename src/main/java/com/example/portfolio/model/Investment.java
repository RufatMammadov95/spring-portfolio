package com.example.portfolio.model;

import jakarta.persistence.*;

@Entity
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double amount;

    @Column(length = 500)
    private String description;

    public Investment() {}

    public Investment(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public Investment(String name, double amount, String description) {
        this.name = name;
        this.amount = amount;
        this.description = description;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public double getAmount() { return amount; }
    public String getDescription() { return description; }

    public void setName(String name) { this.name = name; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setDescription(String description) { this.description = description; }
}