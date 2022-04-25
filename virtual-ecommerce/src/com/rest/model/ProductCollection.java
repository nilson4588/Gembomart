
package com.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_product_collection")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ProductCollection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_collection_id")
	private int productCollectionId;

	@Column(name = "category_id")
	private int categoryId;

	@Column(name = "product_id")
	private int productId;

	public int getProductCollectionId() {
		return productCollectionId;
	}

	public void setProductCollectionId(int productCollectionId) {
		this.productCollectionId = productCollectionId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "ProductCollection [productCollectionId=" + productCollectionId + ", categoryId=" + categoryId
				+ ", productId=" + productId + "]";
	}
}
