package com.example.portfolio.service;

import com.example.portfolio.model.Investment;
import com.example.portfolio.repository.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestmentService {

    @Autowired
    private InvestmentRepository repo;

    private double totalFund = 10000000;

    public List<Investment> getAll() {
        return repo.findAll();
    }

    public Investment save(Investment inv) {
        return repo.save(inv);
    }

    public Investment getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void update(Long id, String name) {
        Investment inv = getById(id);
        if (inv != null) {
            inv.setName(name);
            repo.save(inv);
        }
    }

    public double getRemainingFund() {
        double used = repo.findAll()
                .stream()
                .mapToDouble(Investment::getAmount)
                .sum();
        return totalFund - used;
    }

    public void addToFund(double amount) {
        totalFund += amount;
    }
}