package ru.topacademy.spring_expensetracker;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepo expenseRepository;

    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
    
    public CompletableFuture<Expense> addExpenseAsync(Expense expense)
    {
    	return CompletableFuture.supplyAsync(() -> addExpense(expense));
    }


    public List<Expense> getAllExpenses() {
        return (List<Expense>) expenseRepository.findAll();
    }
    
    public List<Expense> getExpensesByUser(User user) {
        return expenseRepository.findByUser(user);
    }
    
    public CompletableFuture<List<Expense>> getExpensesByUserAsync(User user)
    {
    	return CompletableFuture.supplyAsync(() -> getExpensesByUser(user));
    }
}
