package com.ecommerce.slasa.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import com.ecommerce.slasa.Enums.OrderStage;
import com.ecommerce.slasa.Enums.OrderStatus;

@Entity
@Table(name = "orders")
public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9074775539653199015L;

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,orphanRemoval=true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime orderDate;
    
    @Enumerated(EnumType.STRING)
    private OrderStage stage;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    
    

    
    private double totalUnitGstPrice;
    
    private double totalSellGstPrice;
    
    private double totalAmount;
    
    private double totalGst;
    
	@Column(name="order_id",unique = true, nullable = false)
    private String orderId;

    
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval=true)
    private TransactionDetailModel transactionDetail;
    
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private UserAddress deliveryAddress;
    
	private double totalUnitPrice;
	private double totalSellPrice; 
	private String currency;
	private String currencySymbol;
	
	 public void updateTotalGst() {
	        this.totalGst = this.totalSellGstPrice; 
	 }
	 
	 public void updateTotal() {
	        this.totalAmount=this.totalSellPrice+this.totalSellGstPrice;
	    }
	 
	 public void generateOrderId() {
		    LocalDateTime now = LocalDateTime.now();
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss"); // Format: YYYYMMDDHHmmss
	        String formattedDate = orderDate.format(formatter);
		    String uniqueId = UUID.randomUUID().toString().substring(0, 8); // Get first 8 characters of UUID
		    this.orderId="ORD"+formattedDate+uniqueId.toUpperCase();
		}
	 
	 public Order() {
			super();
		}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public OrderStage getStage() {
		return stage;
	}

	public void setStage(OrderStage stage) {
		this.stage = stage;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
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

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getTotalGst() {
		return totalGst;
	}

	public void setTotalGst(double totalGst) {
		this.totalGst = totalGst;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public TransactionDetailModel getTransactionDetail() {
		return transactionDetail;
	}

	public void setTransactionDetail(TransactionDetailModel transactionDetail) {
		this.transactionDetail = transactionDetail;
	}

	public UserAddress getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(UserAddress deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
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

	public String getOrderid() {
		return orderId;
	}

	public void setOrderid(String orderid) {
		this.orderId = orderid;
	}
	    
	
	
	
	    
	    
	    
	   
  }


