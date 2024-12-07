package com.ecommerce.slasa.Model;



import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.ecommerce.slasa.Enums.AddressType;

@Entity
public class UserAddress {

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    private String address;
    private String selectedOrigin;
    private String city;
    private String area;
    private String street;
    private String office;
    private String pobox;
    private String postCode;
    private String phoneNumber;
    private String selectedCountry;
    private String airport;
    private String seaport;

    @ElementCollection(targetClass = AddressType.class)
    @CollectionTable(name = "address_type")
    @Enumerated(EnumType.STRING)
    private Set<AddressType> addressTypes;
    
    @ManyToOne
    private UserModel user;

    // Getters and Setters

   

	public String getId() {
        return id;
    }

    public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSelectedOrigin() {
        return selectedOrigin;
    }

    public void setSelectedOrigin(String selectedOrigin) {
        this.selectedOrigin = selectedOrigin;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getPobox() {
        return pobox;
    }

    public void setPobox(String pobox) {
        this.pobox = pobox;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(String selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getSeaport() {
        return seaport;
    }

    public void setSeaport(String seaport) {
        this.seaport = seaport;
    }

	public Set<AddressType> getAddressTypes() {
		return addressTypes;
	}

	public void setAddressTypes(Set<AddressType> addressTypes) {
		this.addressTypes = addressTypes;
	}

   
}

