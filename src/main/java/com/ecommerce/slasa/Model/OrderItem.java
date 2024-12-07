package com.ecommerce.slasa.Model;



import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ecommerce.slasa.Enums.OrderStatus;


@Entity
@Table(name = "order_items")
public class OrderItem implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 8339296494169361517L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "order_ids")
    private Order order;

    private String itemName;
    private int quantity;
    private double gst;
    private String productId;
    
    private double sellPrice;
    
    private Double totalAmount;
    
    
    private String imageUrl;  
    private String size;
    private String name;  
    
    
    private String currency;
    private String currencySymbol;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private Admin seller;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    
    
    private LocalDate returnPeriodEndDate;

    private LocalDate deliveryDate;
    
    
    
    
    
    

    public Admin getSeller() {
		return seller;
	}

	public void setSeller(Admin seller) {
		this.seller = seller;
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

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

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

    

    // Getters, setters, and other methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }
    
    

    public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setOrder(Order order) {
        this.order = order;
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
    
    

	public LocalDate getReturnPeriodEndDate() {
		return returnPeriodEndDate;
	}

	public void setReturnPeriodEndDate(LocalDate returnPeriodEndDate) {
		this.returnPeriodEndDate = returnPeriodEndDate;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public double getGst() {
		return gst;
	}

	public void setGst(double gst) {
		this.gst = gst;
	}

	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Double GetGstPrice(Double price,Double gst,Integer qty) {


        double gstAmount = ((price * gst) / 100.0)*qty;

	 
	    DecimalFormat decimalFormat = new DecimalFormat("#.##");

	    
	    return Double.parseDouble(decimalFormat.format(gstAmount));
	    	
}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
    
	
	
}

