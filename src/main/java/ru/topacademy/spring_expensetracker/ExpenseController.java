package ru.topacademy.spring_expensetracker;

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
        return "expenses"; // Maps to WEB-INF/expenses.jsp
    }

    @PostMapping("/add")
    public String addExpense(@RequestParam String reason, @RequestParam Long money) {
        Expense expense = new Expense();
        expense.setReason(reason);
        expense.setExpense(money);
        expenseService.addExpense(expense);
        return "redirect:/";
    }
}
