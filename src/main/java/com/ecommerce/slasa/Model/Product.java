package com.ecommerce.slasa.Model;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.ecommerce.slasa.Validation.ProductValidValues;


@Entity
@Table(name="product")
@ProductValidValues
public class Product implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
	private String Id;
	
    @Column(name = "product_id")
    private String productId;
    
    
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> images;
	
	
//	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<PriceList> priceList;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name="category_path")
	private String categoryPath;
	
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Admin seller;
    
    

    @Column(name = "origin")
    private String origin;

    @Column(name = "private_label")
    private String privateLabel;

    @Column(name = "availability")
    private String availability;

    @Column(name = "status")
    private String status;

    @Column(name = "added_by")
    private String addedBy;

    @Column(name = "stock_location")
    private String stockLocation;

    @Column(name = "temperature")
    private String temperature;

    @Column(name = "buynow")
    private String buynow;
    
    @Column(name="unit_price")
    private String unitPrice;
    
    @Column(name="sell_price")
    private String sellPrice;

    @Column(name = "readytoship")
    private String readytoship;

    @Column(name = "product_hgt")
    private Double productHgt;

    @Column(name = "product_lgh")
    private Double productLgh;

    @Column(name = "product_wdh")
    private Double productWdh;

    @Column(name = "product_wgt")
    private Double productWgt;

    @Column(name = "dimension_unit")
    private String dimensionUnit;

    @Column(name = "transportation_mode")
    private String transportationMode;

    @Column(name = "avg_lead_time")
    private Integer avgLeadTime;

    @Column(name = "size")
    private Double size;

    @Column(name = "units_per_carton")
    private Integer unitsPerCarton;

    @Column(name = "available_quantity")
    private Integer availableQuantity;

    @Column(name = "carton_hgt")
    private Double cartonHgt;

    @Column(name = "carton_lgh")
    private Double cartonLgh;

    @Column(name = "carton_wdh")
    private Double cartonWdh;

    @Column(name = "carton_wgt")
    private Double cartonWgt;

    @Column(name = "sku")
    private String sku; 

    @Column(name = "unit_measure")
    private String unitmeasure;

    @Column(name = "barcode_num")
    private String barcodeNum;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "dgr_goods")
    private String dgrGoods;

    @Column(name = "gender")
    private String gender;

    @Column(name = "colors")
    private String colors;

    @Column(name = "key_words")
    private String keyWords;

    @Column(name = "key_features")
    private String keyFeatures;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "product_name")
    private String productName;
    @Column(name = "currency")
    private String marketCurrency;


    @UpdateTimestamp
    @Column(name="updated_at")
    private Timestamp updatedAt;

    @Transient
    private boolean flag;
    

    @Column(name = "variant_color")
    private String variantColor;

    @Column(name = "variant_size")
    private String variantSize;

    @Column(name = "add_info",columnDefinition = "TEXT")
    private String addInfo;

    @Column(name = "size_unit")
    private String sizeUnit;

    @Column(name = "carton_wgt_unit")
    private String cartonWgtUnit;

    @Column(name = "carton_lgh_unit")
    private String cartonLghUnit;

    @Column(name = "carton_hgt_unit")
    private String cartonHgtUnit;

    @Column(name = "carton_wdh_unit")
    private String cartonWdhUnit;

    @Column(name = "product_wgt_unit")
    private String productWgtUnit;

    @Column(name = "item_model_number")
    private String itemModelNumber;

    @Column(name = "incoterm")
    private String incoterm;

    @Column(name = "lens_type")
    private String lensType;
    
    
    @Column(name = "item_size")
    private String itemSize;
    
    @Column(name = "bullet_points")
    private String bulletPoints;
    
    
    @Column(name = "key_description1",columnDefinition = "TEXT")
    private String keyDescription1;
    
    @Column(name = "key_product_description1",columnDefinition = "TEXT")
    private String keyProductDescription1;
    
    
    @Column(name = "shelf_life", length = 100) // Adjust the length as needed
    private String shelfLife;

    @Column(name = "ingredients", length = 255) // Adjust the length as needed
    private String ingredients;

    @Column(name = "pack_type", length = 50) // Adjust the length as needed
    private String packType;

    @Column(name = "instructions", length = 255) // Adjust the length as needed
    private String instructions;

    @Column(name = "port_type", length = 50) // Adjust the length as needed
    private String portType;

    @Column(name = "connectivity_type", length = 50) // Adjust the length as needed
    private String connectivityType;

    @Column(name = "avg_battery_life", length = 50) // Adjust the length as needed
    private String avgBatteryLife;

    @Column(name = "compatibility", length = 100) // Adjust the length as needed
    private String compatibility;

    @Column(name = "memory_storage", length = 100) // Adjust the length as needed
    private String memoryStorage;

    @Column(name = "version", length = 50) // Adjust the length as needed
    private String version;

    @Column(name = "op_system", length = 50) // Adjust the length as needed
    private String opSystem;

    @Column(name = "screen_size", length = 50) // Adjust the length as needed
    private String screenSize;

    @Column(name = "ram", length = 50) // Adjust the length as needed
    private String ram;

    @Column(name = "specifications", length = 255) // Adjust the length as needed
    private String specifications;

    @Column(name = "fit_size", length = 50) // Adjust the length as needed
    private String fitSize;

    @Column(name = "form", length = 50) // Adjust the length as needed
    private String form;

    @Column(name = "skin_type", length = 50) // Adjust the length as needed
    private String skinType;

    @Column(name = "product_voltage", length = 50) // Adjust the length as needed
    private String voltage;

    @Column(name = "power", length = 50) // Adjust the length as needed
    private String power;

    @Column(name = "power_plug_type", length = 50) // Adjust the length as needed
    private String powerPlugType;

    @Column(name = "product_condition", length = 50) // Adjust the length as needed
    private String condition;

    @Column(name = "pattern", length = 50) // Adjust the length as needed
    private String pattern;

    @Column(name = "flavour", length = 50) // Adjust the length as needed
    private String flavour;

    @Column(name = "pet_size", length = 50) // Adjust the length as needed
    private String petSize;

    @Column(name = "age_range", length = 50) // Adjust the length as needed
    private String ageRange;

    @Column(name = "power_source", length = 50) // Adjust the length as needed
    private String powerSource;

    @Column(name = "min_order_quant") // No length specified for Integer
    private Integer minOrderQuant;
    
    

    private String shelflife;
    
    
    private String flavor;


    
    private Double rating; // Average rating
    private Long numberOfReviews; // Number of reviews
    private Long salesVolume; // Sales volume
    private Double relevanceScore; // Relevance score
    
    
    
    

    public Double getRating() {
		return rating;
	}



	public void setRating(Double rating) {
		this.rating = rating;
	}



	public Long getNumberOfReviews() {
		return numberOfReviews;
	}



	public void setNumberOfReviews(Long numberOfReviews) {
		this.numberOfReviews = numberOfReviews;
	}



	public Long getSalesVolume() {
		return salesVolume;
	}



	public void setSalesVolume(Long salesVolume) {
		this.salesVolume = salesVolume;
	}



	



	public Double getRelevanceScore() {
		return relevanceScore;
	}



	public void setRelevanceScore(Double relevanceScore) {
		this.relevanceScore = relevanceScore;
	}



	public String getShelflife() {
		return shelflife;
	}



	public void setShelflife(String shelflife) {
		this.shelflife = shelflife;
	}



	public String getFlavor() {
		return flavor;
	}



	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}



	public Integer getMinOrderQuant() {
		return minOrderQuant;
	}



	public void setMinOrderQuant(Integer minOrderQuant) {
		this.minOrderQuant = minOrderQuant;
	}



	public String getCategoryPath() {
		return categoryPath;
	}



	public void setCategoryPath(String categoryPath) {
		this.categoryPath = categoryPath;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	public Admin getSeller() {
		return seller;
	}



	public void setSeller(Admin seller) {
		this.seller = seller;
	}



	public String getShelfLife() {
		return shelfLife;
	}



	public String getBulletPoints() {
		return bulletPoints;
	}



	public void setBulletPoints(String bulletPoints) {
		this.bulletPoints = bulletPoints;
	}



	public void setShelfLife(String shelfLife) {
		this.shelfLife = shelfLife;
	}



	public String getIngredients() {
		return ingredients;
	}



	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}



	public String getPackType() {
		return packType;
	}



	public void setPackType(String packType) {
		this.packType = packType;
	}



	public String getInstructions() {
		return instructions;
	}



	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}



	public String getPortType() {
		return portType;
	}



	public void setPortType(String portType) {
		this.portType = portType;
	}



	public String getConnectivityType() {
		return connectivityType;
	}



	public void setConnectivityType(String connectivityType) {
		this.connectivityType = connectivityType;
	}



	public String getAvgBatteryLife() {
		return avgBatteryLife;
	}



	public void setAvgBatteryLife(String avgBatteryLife) {
		this.avgBatteryLife = avgBatteryLife;
	}



	public String getCompatibility() {
		return compatibility;
	}



	public void setCompatibility(String compatibility) {
		this.compatibility = compatibility;
	}



	public String getMemoryStorage() {
		return memoryStorage;
	}



	public void setMemoryStorage(String memoryStorage) {
		this.memoryStorage = memoryStorage;
	}



	public String getVersion() {
		return version;
	}



	public void setVersion(String version) {
		this.version = version;
	}



	public String getOpSystem() {
		return opSystem;
	}



	public void setOpSystem(String opSystem) {
		this.opSystem = opSystem;
	}



	public String getScreenSize() {
		return screenSize;
	}



	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}



	public String getRam() {
		return ram;
	}



	public void setRam(String ram) {
		this.ram = ram;
	}



	public String getSpecifications() {
		return specifications;
	}



	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}



	public String getFitSize() {
		return fitSize;
	}



	public void setFitSize(String fitSize) {
		this.fitSize = fitSize;
	}



	public String getForm() {
		return form;
	}



	public void setForm(String form) {
		this.form = form;
	}



	public String getSkinType() {
		return skinType;
	}



	public void setSkinType(String skinType) {
		this.skinType = skinType;
	}



	public String getVoltage() {
		return voltage;
	}



	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}



	public String getPower() {
		return power;
	}



	public void setPower(String power) {
		this.power = power;
	}



	public String getPowerPlugType() {
		return powerPlugType;
	}



	public void setPowerPlugType(String powerPlugType) {
		this.powerPlugType = powerPlugType;
	}



	public String getCondition() {
		return condition;
	}



	public void setCondition(String condition) {
		this.condition = condition;
	}



	public String getPattern() {
		return pattern;
	}



	public void setPattern(String pattern) {
		this.pattern = pattern;
	}



	public String getFlavour() {
		return flavour;
	}



	public void setFlavour(String flavour) {
		this.flavour = flavour;
	}



	public String getPetSize() {
		return petSize;
	}



	public void setPetSize(String petSize) {
		this.petSize = petSize;
	}



	public String getAgeRange() {
		return ageRange;
	}



	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}



	public String getPowerSource() {
		return powerSource;
	}



	public void setPowerSource(String powerSource) {
		this.powerSource = powerSource;
	}





	



	public void setAvgLeadTime(Integer avgLeadTime) {
		this.avgLeadTime = avgLeadTime;
	}





	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}




	public String getVisibility() {
		return visibility;
	}

    
    
	public String getItemSize() {
		return itemSize;
	}



	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}



	public String getKeyDescription1() {
		return keyDescription1;
	}



	public void setKeyDescription1(String keyDescription1) {
		this.keyDescription1 = keyDescription1;
	}



	public String getKeyProductDescription1() {
		return keyProductDescription1;
	}



	public void setKeyProductDescription1(String keyProductDescription1) {
		this.keyProductDescription1 = keyProductDescription1;
	}



	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	@Column(name = "hts_code")
    private String hsnCode;

    @Column(name = "material")
    private String material;
    
    
    @Column(name = "visibility")
    private String visibility;


    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp time;

	

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<ProductImage> getImages() {
		return images;
	}

	public void setImages(List<ProductImage> images) {
		this.images = images;
	}

//	public List<PriceList> getPriceList() {
//		return priceList;
//	}
//
//	public void setPriceList(List<PriceList> priceList) {
//		this.priceList = priceList;
//	}

	public String getOrigin() {
		return origin;
	}
	
	

	public String getMarketCurrency() {
		return marketCurrency;
	}

	public void setMarketCurrency(String marketCurrency) {
		this.marketCurrency = marketCurrency;
	}

	

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getPrivateLabel() {
		return privateLabel;
	}

	public void setPrivateLabel(String privateLabel) {
		this.privateLabel = privateLabel;
	}

	public String getAvailability() {
		return availability;
	}
	

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public String getStockLocation() {
		return stockLocation;
	}

	public void setStockLocation(String stockLocation) {
		this.stockLocation = stockLocation;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getBuynow() {
		return buynow;
	}

	public void setBuynow(String buynow) {
		this.buynow = buynow;
	}

	public String getReadytoship() {
		return readytoship;
	}

	public void setReadytoship(String readytoship) {
		this.readytoship = readytoship;
	}

	public Integer getUnitsPerCarton() {
		return unitsPerCarton;
	}



	public void setUnitsPerCarton(Integer unitsPerCarton) {
		this.unitsPerCarton = unitsPerCarton;
	}



	

	public Integer getAvgLeadTime() {
		return avgLeadTime;
	}



	



	public Integer getAvailableQuantity() {
		return availableQuantity;
	}








	public String getDimensionUnit() {
		return dimensionUnit;
	}

	public void setDimensionUnit(String dimensionUnit) {
		this.dimensionUnit = dimensionUnit;
	}

	public String getTransportationMode() {
		return transportationMode;
	}

	public void setTransportationMode(String transportationMode) {
		this.transportationMode = transportationMode;
	}

	


	public String getSku() {
		return sku;
	}



	public void setSku(String sku) {
		this.sku = sku;
	}



	public String getUnitmeasure() {
		return unitmeasure;
	}

	public void setUnitmeasure(String unitmeasure) {
		this.unitmeasure = unitmeasure;
	}

	public String getBarcodeNum() {
		return barcodeNum;
	}

	public void setBarcodeNum(String barcodeNum) {
		this.barcodeNum = barcodeNum;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getDgrGoods() {
		return dgrGoods;
	}

	public void setDgrGoods(String dgrGoods) {
		this.dgrGoods = dgrGoods;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getColors() {
		return colors;
	}

	public void setColors(String colors) {
		this.colors = colors;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getKeyFeatures() {
		return keyFeatures;
	}

	public void setKeyFeatures(String keyFeatures) {
		this.keyFeatures = keyFeatures;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	

	

	public String getVariantColor() {
		return variantColor;
	}

	public void setVariantColor(String variantColor) {
		this.variantColor = variantColor;
	}

	public String getVariantSize() {
		return variantSize;
	}

	public void setVariantSize(String variantSize) {
		this.variantSize = variantSize;
	}

	public String getAddInfo() {
		return addInfo;
	}

	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}

	public String getSizeUnit() {
		return sizeUnit;
	}

	public void setSizeUnit(String sizeUnit) {
		this.sizeUnit = sizeUnit;
	}

	public String getCartonWgtUnit() {
		return cartonWgtUnit;
	}

	public void setCartonWgtUnit(String cartonWgtUnit) {
		this.cartonWgtUnit = cartonWgtUnit;
	}

	public String getCartonLghUnit() {
		return cartonLghUnit;
	}

	public void setCartonLghUnit(String cartonLghUnit) {
		this.cartonLghUnit = cartonLghUnit;
	}

	public String getCartonHgtUnit() {
		return cartonHgtUnit;
	}

	public void setCartonHgtUnit(String cartonHgtUnit) {
		this.cartonHgtUnit = cartonHgtUnit;
	}

	public String getCartonWdhUnit() {
		return cartonWdhUnit;
	}

	public void setCartonWdhUnit(String cartonWdhUnit) {
		this.cartonWdhUnit = cartonWdhUnit;
	}

	public String getProductWgtUnit() {
		return productWgtUnit;
	}

	public void setProductWgtUnit(String productWgtUnit) {
		this.productWgtUnit = productWgtUnit;
	}

	public String getItemModelNumber() {
		return itemModelNumber;
	}

	public void setItemModelNumber(String itemModelNumber) {
		this.itemModelNumber = itemModelNumber;
	}

	public String getIncoterm() {
		return incoterm;
	}

	public void setIncoterm(String incoterm) {
		this.incoterm = incoterm;
	}

	public String getLensType() {
		return lensType;
	}

	public void setLensType(String lensType) {
		this.lensType = lensType;
	}

	

	public String getHsnCode() {
		return hsnCode;
	}



	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}



	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	public Boolean isAvailable(Product product) {
		return product.getAvailableQuantity()>0;
	}



	public Double getProductHgt() {
		return productHgt;
	}



	public void setProductHgt(Double productHgt) {
		this.productHgt = productHgt;
	}



	public Double getProductLgh() {
		return productLgh;
	}



	public void setProductLgh(Double productLgh) {
		this.productLgh = productLgh;
	}



	public Double getProductWdh() {
		return productWdh;
	}



	public void setProductWdh(Double productWdh) {
		this.productWdh = productWdh;
	}



	public Double getProductWgt() {
		return productWgt;
	}



	public void setProductWgt(Double productWgt) {
		this.productWgt = productWgt;
	}



	public Double getSize() {
		return size;
	}



	public void setSize(Double size) {
		this.size = size;
	}



	public Double getCartonHgt() {
		return cartonHgt;
	}



	public void setCartonHgt(Double cartonHgt) {
		this.cartonHgt = cartonHgt;
	}



	public Double getCartonLgh() {
		return cartonLgh;
	}



	public void setCartonLgh(Double cartonLgh) {
		this.cartonLgh = cartonLgh;
	}



	public Double getCartonWdh() {
		return cartonWdh;
	}



	public void setCartonWdh(Double cartonWdh) {
		this.cartonWdh = cartonWdh;
	}



	public Double getCartonWgt() {
		return cartonWgt;
	}



	public void setCartonWgt(Double cartonWgt) {
		this.cartonWgt = cartonWgt;
	}
	
	
	

    // Getters and setters
    
    
}
