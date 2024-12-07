package com.ecommerce.slasa.Model;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "carts")
public class Cart {

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    
    
	    private double totalUnitPrice;
	    private double totalSellPrice;
	    
	    private double totalUnitGstPrice;
	    private double totalSellGstPrice;
    
    
    
    private String currency;
    
    private String currencySymbol;
    
    
    
    
//      public void updateTotal() {
//        double newTotal = 0.0;
//        double newUnitTotal=0.0;
//         double UnitGstPrice=0.0;
//         double SellGstPrice=0.0;
//        
//        
//        for (CartItem item : cartItems) {
//        	UnitGstPrice+=GetGstPrice(item.getUnitPrice(),item.getGst(),item.getQuantity());
//        	newUnitTotal += (item.getQuantity() * item.getUnitPrice());
//
//        }
//        for (CartItem item : cartItems) {
//        	SellGstPrice+=+GetGstPrice(item.getSellPrice(),item.getGst(),item.getQuantity());
//            newTotal += (item.getQuantity() * item.getSellPrice());
//
//        }
//
//        this.totalSellPrice = newTotal;
//        this.totalUnitPrice=newUnitTotal;
//        this.totalSellGstPrice=SellGstPrice;
//        this.totalUnitGstPrice=UnitGstPrice;
//    }
    
    public Double GetGstPrice(Double price,Double gst,Integer qty) {


        double gstAmount = ((price * gst) / 100.0)*qty;

	 
	    DecimalFormat decimalFormat = new DecimalFormat("#.##");

	    
	    return Double.parseDouble(decimalFormat.format(gstAmount));
	    	
}
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}



	public Timestamp getLastUpatedDate() {
		return lastUpatedDate;
	}

	public void setLastUpatedDate(Timestamp lastUpatedDate) {
		this.lastUpatedDate = lastUpatedDate;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@UpdateTimestamp
    private Timestamp lastUpatedDate;
    
    @CreationTimestamp
    private Timestamp createdDate;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(Long id) {
		
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public double getTotalUnitPrice() {
		return totalUnitPrice;
	}

	public void setTotalUnitPrice(double totalUnitPrice) {
		this.totalUnitPrice = totalUnitPrice;
	}

	public double getTotalSellPrice() {
		return totalSellPrice;
	}

	public void setTotalSellPrice(double totalSellPrice) {
		this.totalSellPrice = totalSellPrice;
	}

	public double getTotalUnitGstPrice() {
		return totalUnitGstPrice;
	}

	public void setTotalUnitGstPrice(double totalUnitGstPrice) {
		this.totalUnitGstPrice = totalUnitGstPrice;
	}

	public double getTotalSellGstPrice() {
		return totalSellGstPrice;
	}

	public void setTotalSellGstPrice(double totalSellGstPrice) {
		this.totalSellGstPrice = totalSellGstPrice;
	}
    
    
    
    


  
}

