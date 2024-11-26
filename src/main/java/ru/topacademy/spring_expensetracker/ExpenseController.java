package ru.topacademy.spring_expensetracker;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

@Controller
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private UserService userService;
    
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password!");
        }
        if (logout != null) {
            model.addAttribute("successMessage", "You have been logged out successfully.");
        }
        model.addAttribute("user", new User());
        return "login"; // Return the custom login view
    }
    
    @GetMapping("/registration")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(Model model, @ModelAttribute User user) {
        // Add user to your user service or database
        // Ensure password encoding is consistent with `BCryptPasswordEncoder`
        userService.addUser(user);
        return "redirect:/login";
    }

    @GetMapping("/")
    public String showExpenses(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String email = auth.getName();
    	List<Expense> expenses = null;
 
			User loggedInUser = userService.findByEmail(email);
			expenses = expenseService.getExpensesByUser(loggedInUser);
	
    	
        model.addAttribute("expenses", expenses);
        model.addAttribute("expense", new Expense());
        return "index"; 
    }

    @PostMapping("/add")
    public String addExpense(@ModelAttribute Expense expense, Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String email = auth.getName();
    	User user = userService.findByEmail(email);
    	
        expense.setDate(new Date());
        expense.setUser(user);
        expenseService.addExpenseAsync(expense);
        model.addAttribute("expense", expense);
        return "redirect:/";
    }
}
