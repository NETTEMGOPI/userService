package com.gopi.EventDrivenArch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailEventProducer emailEventProducer;

	public Users CreateUser(String userName,String password, String firstName, String emailID)
	{
		Users users=new Users();
		users.setUsername(userName);
		users.setPassword(password);
		users.setFirstName(firstName);
		users.setEmailID(emailID);
		
//		return userRepository.save(user);
		Users savedUser = userRepository.save(users);
		System.out.println("✅ User saved to database: " + savedUser.getUsername());

		// Send email event to Kafka
		emailEventProducer.sendEmailEvent(savedUser);

		return savedUser;
	}

}
