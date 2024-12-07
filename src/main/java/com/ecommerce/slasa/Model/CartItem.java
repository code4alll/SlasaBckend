package com.ecommerce.slasa.Model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private Admin seller;

    private String itemName;
    private double sellPrice;
    
    public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	private double unitPrice;
    private int quantity;
    private Double gst;
    private String productId;
    private String currency;
    private String currencySymbol;
    
    private String imageUrl;  
    private String size;
    private String name;  
    
    private Integer minOrderQuant;
    private Integer unitsPerCarton;
    
    private Double totalAmount;

    @UpdateTimestamp
    private Timestamp lastUpatedDate;
    
    @CreationTimestamp
    private Timestamp createdDate;

    // Getters, setters, and other methods

    public Long getId() {
        return id;
    }
    
    

    public Integer getMinOrderQuant() {
		return minOrderQuant;
	}

	public void setMinOrderQuant(Integer minOrderQuant) {
		this.minOrderQuant = minOrderQuant;
	}

	public Integer getUnitsPerCarton() {
		return unitsPerCarton;
	}

	public void setUnitsPerCarton(Integer unitsPerCarton) {
		this.unitsPerCarton = unitsPerCarton;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }
    
    

    public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

   

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    

	public Double getGst() {
		return gst;
	}

	public void setGst(Double gst) {
		this.gst = gst;
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

	
	

	
	

	public CartItem(Cart cart, String itemName, double unitPrice,double sellPrice,  int quantity, Double gst, String productId) {
		super();
		this.cart = cart;
		this.itemName = itemName;
		this.unitPrice=unitPrice;
		this.sellPrice=sellPrice;
		this.quantity = quantity;
		this.gst = gst;
		this.productId = productId;
	}

	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void updateCartTotal(Double amount,Integer qty) {
		this.setTotalAmount(amount*qty);
	}

	public Admin getSeller() {
		return seller;
	}

	public void setSeller(Admin seller) {
		this.seller = seller;
	}
	
    
    
}

