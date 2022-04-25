package com.rest.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_product")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name = "product_id")
	private int id;

	@Column(name = "product_title")
	private String title;

	@Column(name = "product_description")
	private String description;

	@Column(name = "product_type")
	private String type;

	@Column(name = "product_brand")
	private String brand;

	@Transient
	private List<String> collection;

	@Column(name = "product_category")
	private String category;

	@Column(name = "product_packsize")
	private String packsize;

	@Column(name = "product_price")
	private double price;

	@Column(name = "product_sale")
	private boolean sale;

	@Column(name = "product_discount")
	private String discount;

	@Column(name = "product_stock")
	private int stock;

	@Column(name = "product_new")
	private boolean isnew;

	@Column(name = "is_active")
	private int isActive;
		
	@Transient
	private List<String> tags;

	@Transient
	private List<Variants> variants;

	@Transient
	private List<Images> images;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public List<String> getCollection() {
		return collection;
	}

	public void setCollection(List<String> collection) {
		this.collection = collection;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPacksize() {
		return packsize;
	}

	public void setPacksize(String packsize) {
		this.packsize = packsize;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isSale() {
		return sale;
	}

	public void setSale(boolean sale) {
		this.sale = sale;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public boolean isIsnew() {
		return isnew;
	}

	public void setIsnew(boolean isnew) {
		this.isnew = isnew;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<Variants> getVariants() {
		return variants;
	}

	public void setVariants(List<Variants> variants) {
		this.variants = variants;
	}

	public List<Images> getImages() {
		return images;
	}

	public void setImages(List<Images> images) {
		this.images = images;
	}
	
	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", description=" + description + ", type=" + type + ", brand="
				+ brand + ", collection=" + collection + ", category=" + category + ", packsize=" + packsize
				+ ", price=" + price + ", sale=" + sale + ", discount=" + discount + ", stock=" + stock + ", isnew="
				+ isnew + ", isActive=" + isActive + ", tags=" + tags + ", variants=" + variants + ", images=" + images
				+ "]";
	}	
}