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
@Table(name = "tbl_product_images")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Images {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name = "product_image_id")
	private int image_id;

	@Column(name = "product_id")
	private int id;

	@Column(name = "image_alt")
	private String alt;

	@Column(name = "image_src")
	private String src;

	@Transient
	private List<Integer> variant_id;

	public int getImage_id() {
		return image_id;
	}

	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public List<Integer> getVariant_id() {
		return variant_id;
	}

	public void setVariant_id(List<Integer> variant_id) {
		this.variant_id = variant_id;
	}

	@Override
	public String toString() {
		return "Images [image_id=" + image_id + ", id=" + id + ", alt=" + alt + ", src=" + src + ", variant_id="
				+ variant_id + "]";
	}
}