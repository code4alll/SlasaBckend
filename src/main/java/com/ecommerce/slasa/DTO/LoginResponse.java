package com.ecommerce.slasa.DTO;

import java.util.List;

import com.ecommerce.slasa.Enums.Roles;

public class LoginResponse {
	
	private String firstname;
	private String lastname;

	private String token;
	private Boolean status;
	private String message;
	List<AddressResponseDTO> useraddress;
	private String email;
	private Roles role;
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<AddressResponseDTO> getUseraddress() {
		return useraddress;
	}
	public void setUseraddress(List<AddressResponseDTO> useraddress) {
		this.useraddress = useraddress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
	}
	public LoginResponse(String name, String token, Boolean status, String message, Roles role,List<AddressResponseDTO> address,String country,String countryOfoperation) {
		super();
		this.token = token;
		this.status=status;
		this.message = message;
		this.role = role;
		this.useraddress=address;
		
	}
	public LoginResponse() {
		// TODO Auto-generated constructor stub
	}
	

    
}
