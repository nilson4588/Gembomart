
package com.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_product_tags")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ProductTags {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_tag_id")
	private int productTagId;

	@Column(name = "tag_id")
	private int tagId;

	@Column(name = "product_id")
	private int productId;

	public int getProductTagId() {
		return productTagId;
	}

	public void setProductTagId(int productTagId) {
		this.productTagId = productTagId;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
}
