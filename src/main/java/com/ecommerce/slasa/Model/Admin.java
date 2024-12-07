package com.ecommerce.slasa.Model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;


@Entity
public class Admin extends UserModel {
	
	
   


	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Admin(String username, String password, String email, String firstname, String lastname, String country) {
		super(username, password, email, firstname, lastname, country);
		// TODO Auto-generated constructor stub
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = -6251797078012896058L;

	@OneToMany(mappedBy = "seller", fetch=FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SellerBankDetails> bankDetails;
    
    @OneToMany(mappedBy = "seller",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<OrderItem> orders;  
    

	@OneToMany(mappedBy = "seller",fetch=FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval=true)
    private List<Product> product;
	
}
