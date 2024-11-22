package ru.topacademy.spring_expensetracker;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User addUser(User user)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repo.save(user);
	}
	
	public User findUser(Long id)
	{
		Optional<User> user = repo.findById(id);
		if(user.isPresent())
		{
			return user.get();
		}
		else
		{
			return null;
		}
	}
	
	public User findByEmail(String email)
	{
		return repo.findByEmail(email).get(0);
	}
}
