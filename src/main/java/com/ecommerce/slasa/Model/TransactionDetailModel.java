package com.ecommerce.slasa.Model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.ecommerce.slasa.Enums.PaymentGatewayType;
import com.ecommerce.slasa.Enums.PaymentStatus;

@Entity
@Table(name="transaction_details")
public class TransactionDetailModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -129462818035214664L;

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "transaction_id")
    private String transactionId;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "gateway")
    private PaymentGatewayType gateway;

    @Column(name = "mode")
    private String mode;

    @CreationTimestamp
    @Column(name = "creation_time")
    private Timestamp creationTime;

    @UpdateTimestamp
    @Column(name = "updated_time")
    private Timestamp updatedTime;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "currency")
    private String currency;

    
    

    
    
    public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public PaymentGatewayType getGateway() {
		return gateway;
	}

	public void setGateway(PaymentGatewayType gateway) {
		this.gateway = gateway;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Timestamp getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	

	public TransactionDetailModel(String paymentId, PaymentStatus paymentStatus, PaymentGatewayType gateway, String mode) {
		super();
		this.paymentId = paymentId;
		this.paymentStatus = paymentStatus;
		this.gateway = gateway;
		this.mode = mode;
	}
	
	

	public TransactionDetailModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    
    
    
    
	
	

}

