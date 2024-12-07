package com.ecommerce.slasa.Model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ecommerce.slasa.Enums.PaymentMethodType;


@Entity
@Table(name = "payment_method")
public class PaymentMethod {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false)
    private PaymentMethodType paymentType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Common fields for various payment types
    private String accHolderName;
    private String accNo;
    private String bankLocation;
    private String bankName;
    private String swiftbic;
    private String ifsc;
    private String iban;

    private String cardNumber;
    private String expiryDate;
    private String fullname;

    private String upi;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PaymentMethodType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentMethodType paymentType) {
		this.paymentType = paymentType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAccHolderName() {
		return accHolderName;
	}

	public void setAccHolderName(String accHolderName) {
		this.accHolderName = accHolderName;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getBankLocation() {
		return bankLocation;
	}

	public void setBankLocation(String bankLocation) {
		this.bankLocation = bankLocation;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getSwiftbic() {
		return swiftbic;
	}

	public void setSwiftbic(String swiftbic) {
		this.swiftbic = swiftbic;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUpi() {
		return upi;
	}

	public void setUpi(String upi) {
		this.upi = upi;
	}

	
	//bank
	public PaymentMethod(PaymentMethodType paymentType, String accHolderName, String accNo, String bankLocation,
			String bankName, String swiftbic, String ifsc, String iban) {
		super();
		this.paymentType = paymentType;
		this.accHolderName = accHolderName;
		this.accNo = accNo;
		this.bankLocation = bankLocation;
		this.bankName = bankName;
		this.swiftbic = swiftbic;
		this.ifsc = ifsc;
		this.iban = iban;
	}
//card
	public PaymentMethod(PaymentMethodType paymentType, String cardNumber, String expiryDate, String fullname) {
		super();
		this.paymentType = paymentType;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
		this.fullname = fullname;
	}
	
//upi
public PaymentMethod(PaymentMethodType paymentType, String upi) {
	super();
	this.paymentType = paymentType;
	this.upi = upi;
}

public PaymentMethod() {
	super();
	// TODO Auto-generated constructor stub
}
    
	
	
	
	
    
    
    
    
    
    
    
    
    

}
