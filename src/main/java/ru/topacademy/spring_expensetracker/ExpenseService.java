package ru.topacademy.spring_expensetracker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepo expenseRepository;

    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }


    public List<Expense> getAllExpenses() {
        return (List<Expense>) expenseRepository.findAll();
    }
}
