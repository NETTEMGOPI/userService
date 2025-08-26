package com.gopi.EventDrivenArch;

import org.springframework.context.annotation.Primary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Users")
public class Users {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@Column 
	private String username;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column private String password;
	@Column private String firstName;
	@Column private String emailID;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	@Override
	public String toString() {
		return "User [id= " + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName + ", emailID="
				+ emailID + "]";
	}
	public Users(Long id, String username, String password, String firstName, String emailID) {
		super();
		this.id=id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.emailID = emailID;
	}
	
	public Users() {}
}
