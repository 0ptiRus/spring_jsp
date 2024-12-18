package ru.topacademy.spring_expensetracker;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;

@Entity
public class Expense implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private int Id;
    private Date date;
    private Long expense;
    private String reason;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Long getExpense() {
		return expense;
	}
	public void setExpense(Long expense) {
		this.expense = expense;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Expense(Date date, Long expense, String reason, User user) {
		super();
		this.date = date;
		this.expense = expense;
		this.reason = reason;
		this.user = user;
	}
	
	public Expense() {}

   

}