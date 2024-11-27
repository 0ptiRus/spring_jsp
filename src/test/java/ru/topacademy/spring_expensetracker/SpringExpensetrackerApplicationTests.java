package ru.topacademy.spring_expensetracker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.net.ssl.SSLEngineResult.Status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Import(TestSecurityConfig.class)
@AutoConfigureMockMvc
class SpringExpensetrackerApplicationTests {
	
	 @Autowired
	    private MockMvc mockMvc;

	    @Autowired
	    private ExpenseService expenseService; // Service for managing expenses

	    @Autowired
	    private UserService userService;
	    
	    @Autowired
	    private PasswordEncoder passwordEncoder;

	@Test
	void getUrlTest() 
	{
		try {
			mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	@Transactional
	@Test
	void addExpenseTest() throws Exception {
	    // Step 1: Create and save a test user
	    User user = new User();
	    user.setEmail("test");
	    user.setPassword(passwordEncoder.encode("test")); // Ensure password is encoded
	    userService.addUser(user);

	    // Step 2: Authenticate the test user
	    mockMvc.perform(post("/login")
	            .param("email", "test")
	            .param("password", "test"))
	            .andExpect(status().isFound()) // Expect redirect after login
	            .andExpect(redirectedUrl("/")); // Verify successful login

	    // Step 3: Add an expense
	    mockMvc.perform(post("/add")
	            .param("amount", "1000") // Ensure parameters match the controller
	            .param("description", "cow"))
	            .andExpect(status().isFound()); // Expect redirect after successful addition

	    // Step 4: Verify expense was added
	    Expense expense = expenseService.getByReason("cow");
	    assertThat(expense).isNotNull();
	    assertThat(expense.getReason()).isEqualTo("cow");
	    assertThat(expense.getExpense()).isEqualTo(1000);
	    assertThat(expense.getUser()).isEqualTo(user); // Verify user association
	}


}
