package com.ecommerce.slasa.Model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SellerBankDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -705262673344908098L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accHolderName;
    private String accNo;
    private String bankLocation;
    private String bankName;
    private Boolean defaultValue;
    private String swiftbic;
    private String ifsc;
    private String iban;
    
    private String iiban;
    private String iifsc;
    private String ibankName;
    private String iaccNo;
    private String iswiftbic;
    private Boolean isIntermediary;
    
    @ManyToOne
    private Admin seller;


    
    public Admin getSeller() {
		return seller;
	}


	public void setSeller(Admin seller) {
		this.seller = seller;
	}


	public SellerBankDetails(String accHolderName, String accNo, String bankLocation, String bankName, String swiftbic,
			String ifsc) {
		super();
		this.accHolderName = accHolderName;
		this.accNo = accNo;
		this.bankLocation = bankLocation;
		this.bankName = bankName;
		this.swiftbic = swiftbic;
		this.ifsc = ifsc;
	}
	
	
	public SellerBankDetails() {
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Boolean getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(Boolean defaultValue) {
		this.defaultValue = defaultValue;
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
	public String getIiban() {
		return iiban;
	}
	public void setIiban(String iiban) {
		this.iiban = iiban;
	}
	public String getIifsc() {
		return iifsc;
	}
	public void setIifsc(String iifsc) {
		this.iifsc = iifsc;
	}
	public String getIbankName() {
		return ibankName;
	}
	public void setIbankName(String ibankName) {
		this.ibankName = ibankName;
	}
	public String getIaccNo() {
		return iaccNo;
	}
	public void setIaccNo(String iaccNo) {
		this.iaccNo = iaccNo;
	}
	public Boolean getIsIntermediary() {
		return isIntermediary;
	}
	public void setIsIntermediary(Boolean isIntermediary) {
		this.isIntermediary = isIntermediary;
	}
	public String getIswiftbic() {
		return iswiftbic;
	}
	public void setIswiftbic(String iswiftbic) {
		this.iswiftbic = iswiftbic;
	}

    
    
    
    
}
