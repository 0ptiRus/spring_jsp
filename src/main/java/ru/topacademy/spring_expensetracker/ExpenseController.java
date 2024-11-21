package ru.topacademy.spring_expensetracker;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/")
    public String showExpenses(Model model) {
        model.addAttribute("expenses", expenseService.getAllExpenses());
        model.addAttribute("expense", new Expense());
        return "index"; 
    }

    @PostMapping("/add")
    public String addExpense(@ModelAttribute Expense expense, Model model) {
        expense.setDate(new Date());
        expenseService.addExpense(expense);
        model.addAttribute("expense", expense);
        return "redirect:/";
    }
}
