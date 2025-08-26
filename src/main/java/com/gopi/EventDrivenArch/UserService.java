package com.gopi.EventDrivenArch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	public Users CreateUser(String userName,String password, String firstName, String emailID)
	{
		Users user=new Users();
		user.setUsername(userName);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setEmailID(emailID);
		
		return userRepository.save(user);
	}

}
