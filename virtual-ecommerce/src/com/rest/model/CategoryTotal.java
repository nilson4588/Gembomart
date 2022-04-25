package com.rest.model;

public class CategoryTotal {
	
	private double fruits;
	private double grocery;
	private double vegetables;
	
	public double getFruits() {
		return fruits;
	}
	public void setFruits(double fruits) {
		this.fruits = fruits;
	}
	public double getGrocery() {
		return grocery;
	}
	public void setGrocery(double grocery) {
		this.grocery = grocery;
	}
	public double getVegetables() {
		return vegetables;
	}
	public void setVegetables(double vegetables) {
		this.vegetables = vegetables;
	}
	
	@Override
	public String toString() {
		return "CategoryTotal [fruits=" + fruits + ", grocery=" + grocery + ", vegetables=" + vegetables + "]";
	}	
}
