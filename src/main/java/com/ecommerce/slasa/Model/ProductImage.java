package com.ecommerce.slasa.Model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch=FetchType.LAZY ,cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "image_id")
    private Product product;
    
    private String imageUrl;
    
    private String sku;
    
    private Long size;
    private String name;
    private String dimension;
    private int priority;
    
    
    
    
    
    

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getSku() {
		return sku;
	}

	public void setSku(String string) {
		this.sku = string;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public ProductImage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String  getSizeInKb() {
		double sizeInKB =  this.size/ 1024.0;
        String formattedSize = String.format("%.1fKB", sizeInKB);
         return formattedSize;
	}

    // Constructors, getters, setters
}
