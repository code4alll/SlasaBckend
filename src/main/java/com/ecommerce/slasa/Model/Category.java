package com.ecommerce.slasa.Model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;


	@Entity
	public class Category {
	    
		@Id
	    @GeneratedValue(generator = "UUID")
	    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	    @Column(name = "id", updatable = false, nullable = false)
	    private String id;
	    
	    private String name;
	    
	    private Double commision;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "parent_id")
	    private Category parentCategory;
	    
	    @Column(name="created_at")
	    @CreationTimestamp
	    private Timestamp createdAt;
	    
	    @Column(name="updated_at")
	    @UpdateTimestamp
	    private Timestamp updatedAt;
	    
	    private String urlSlug;
	    private Double gst;
	    
	    private String categoryId;
	    
	    private Integer level;
	    

		public Integer getLevel() {
			return level;
		}

		public void setLevel(Integer level) {
			this.level = level;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Category getParentCategory() {
			return parentCategory;
		}

		public void setParentCategory(Category parentCategory) {
			this.parentCategory = parentCategory;
		}

		public Timestamp getCreatedAt() {
			return createdAt;
		}

		public String getUrlSlug() {
			return urlSlug;
		}
		
		

		public void setUrlSlug(String urlSlug) {
			this.urlSlug = urlSlug;
		}

		public Double getGst() {
			return gst;
		}
		

		public String getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(String categoryId) {
			this.categoryId = categoryId;
		}

		public void setGst(Double gst) {
			this.gst = gst;
		}

		public void setCreatedAt(Timestamp createdAt) {
			this.createdAt = createdAt;
		}

		public Timestamp getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(Timestamp updatedAt) {
			this.updatedAt = updatedAt;
		}
		
		

		public Double getCommision() {
			return commision;
		}

		public void setCommision(Double commision) {
			this.commision = commision;
		}

		

		public Category() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Category(String name, Double commision, Category parentCategory, Timestamp createdAt,
				Timestamp updatedAt, String urlSlug, Double gst) {
			super();
			this.name = name;
			this.commision = commision;
			this.parentCategory = parentCategory;
			this.createdAt = createdAt;
			this.updatedAt = updatedAt;
			this.urlSlug = urlSlug;
			this.gst = gst;
		}
	    
	    
	    

	 	}

	
//    private String name;
//    private List<Category> subCategories;
//    private List<String> items;
//    private double marginPercentage; 
//
//    public Category(String name, double marginPercentage) {
//        this.name = name;
//        this.subCategories = new ArrayList<>();
//        this.items = new ArrayList<>();
//        this.marginPercentage = marginPercentage;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<Category> getSubCategories() {
//        return subCategories;
//    }
//
//    public void setSubCategories(List<Category> subCategories) {
//        this.subCategories = subCategories;
//    }
//
//    public List<String> getItems() {
//        return items;
//    }
//
//    public void setItems(List<String> items) {
//        this.items = items;
//    }
//
//    public double getMarginPercentage() {
//        return marginPercentage;
//    }
//
//    public void setMarginPercentage(double marginPercentage) {
//        this.marginPercentage = marginPercentage;
//    }
//}
