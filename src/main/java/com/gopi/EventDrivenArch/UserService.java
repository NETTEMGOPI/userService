package com.gopi.EventDrivenArch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	private EmailEventProducer emailEventProducer;
	public Users CreateUser(String userName,String password, String firstName, String emailID)
	{
		Users user=new Users();
		user.setUsername(userName);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setEmailID(emailID);
		
//		return userRepository.save(user);
		Users savedUser = userRepository.save(user);
		System.out.println("✅ User saved to database: " + savedUser.getUsername());

		// Send email event to Kafka
		emailEventProducer.sendEmailEvent(savedUser);

		return savedUser;
	}

}
