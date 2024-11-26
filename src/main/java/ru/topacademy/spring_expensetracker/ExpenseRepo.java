package ru.topacademy.spring_expensetracker;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepo extends CrudRepository<Expense, Integer> 
{
	List<Expense> findByUser(User user);
}
