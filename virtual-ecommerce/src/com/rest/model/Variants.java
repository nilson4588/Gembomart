
package com.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_product_variant")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Variants {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name = "product_variant_id")
	private int variant_id;

	@Column(name = "product_id")
	private int id;

	@Column(name = "product_sku")
	private String sku;

	@Column(name = "product_color")
	private String color;

	@Column(name = "product_image_id")
	private int image_id;

	public int getVariant_id() {
		return variant_id;
	}

	public void setVariant_id(int variant_id) {
		this.variant_id = variant_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getImage_id() {
		return image_id;
	}

	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}

	@Override
	public String toString() {
		return "Variants [variant_id=" + variant_id + ", id=" + id + ", sku=" + sku + ", color=" + color + ", image_id="
				+ image_id + "]";
	}
}