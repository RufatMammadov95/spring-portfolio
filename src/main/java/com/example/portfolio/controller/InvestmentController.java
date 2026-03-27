package com.example.portfolio.controller;

import com.example.portfolio.model.Investment;
import com.example.portfolio.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Comparator;

@Controller
@RequestMapping("/investments")
public class InvestmentController {

    @Autowired
    private InvestmentService service;

    // LIST PAGE
    @GetMapping("/")
    public String home(@RequestParam(required = false) String sort, Model model) {
        List<Investment> investments = service.getAll();

        if (sort != null) {
            switch (sort) {
                case "amountAsc":
                    investments.sort(Comparator.comparingDouble(Investment::getAmount));
                    break;
                case "amountDesc":
                    investments.sort(Comparator.comparingDouble(Investment::getAmount).reversed());
                    break;
                case "nameAsc":
                    investments.sort(Comparator.comparing(Investment::getName));
                    break;
                case "nameDesc":
                    investments.sort(Comparator.comparing(Investment::getName).reversed());
                    break;
            }
        }

        model.addAttribute("investments", investments);
        model.addAttribute("remaining", service.getRemainingFund());

        return "index"; // index.html list page
    }

    // ADD INVESTMENT
    @PostMapping
    public String add(@RequestParam String name,
                      @RequestParam double amount,
                      @RequestParam(required = false) String description) {
        service.save(new Investment(name, amount, description));
        return "redirect:/investments/";
    }

    // ADD TO FUND
    @PostMapping("/fund")
    public String addFund(@RequestParam double amount) {
        service.addToFund(amount);
        return "redirect:/investments/";
    }

    // UPDATE PAGE
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Long id, Model model) {
        model.addAttribute("investment", service.getById(id));
        return "edit"; // edit.html
    }

    // UPDATE ACTION
    @PostMapping("/update")
    public String update(@RequestParam Long id,
                         @RequestParam String name,
                         @RequestParam double amount,
                         @RequestParam(required = false) String description) {

        Investment inv = service.getById(id);
        if (inv != null) {
            inv.setName(name);
            inv.setAmount(amount);
            inv.setDescription(description);
            service.save(inv);
        }

        return "redirect:/investments/";
    }
    
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Investment inv = service.getById(id);
        if (inv != null) {
            service.deleteById(id);
        }
        return "redirect:/investments/";
    }

    // DETAIL PAGE
    @GetMapping("/{id}")
    public String detailPage(@PathVariable Long id, Model model) {
        Investment inv = service.getById(id);
        if (inv == null) {
            return "redirect:/investments/";
        }
        model.addAttribute("investment", inv);
        return "investment-detail"; // investment-detail.html
    }
}
