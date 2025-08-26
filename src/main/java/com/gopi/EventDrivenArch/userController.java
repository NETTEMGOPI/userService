package com.gopi.EventDrivenArch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class userController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/newUser")
	public Users createNewuser(
			@RequestParam(required=true) String userName,
			@RequestParam(required=true) String password,
			@RequestParam(required=true) String firstName,
			@RequestParam(required=true) String emailID)
	{
		
		return userService.CreateUser(userName,password, firstName, emailID);
		
	}
}
