package com.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_images_variant")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ImagesVariant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name = "image_variant_id")
	private int image_variant_id;

	@Column(name = "image_id")
	private int image_id;

	@Column(name = "variant_id")
	private int variant_id;

	public int getImage_variant_id() {
		return image_variant_id;
	}

	public void setImage_variant_id(int image_variant_id) {
		this.image_variant_id = image_variant_id;
	}

	public int getImage_id() {
		return image_id;
	}

	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}

	public int getVariant_id() {
		return variant_id;
	}

	public void setVariant_id(int variant_id) {
		this.variant_id = variant_id;
	}

	@Override
	public String toString() {
		return "ImagesVariant [image_variant_id=" + image_variant_id + ", image_id=" + image_id + ", variant_id="
				+ variant_id + "]";
	}
}