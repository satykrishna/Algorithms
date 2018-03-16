package java8.lamba.model;

import java.io.Serializable;

public class Fruit implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected Integer weight;
	
	protected String color;

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Fruit(Integer weight, String color) {
		super();
		this.weight = weight;
		this.color = color;
	}

	public Fruit(Integer weight) {
		super();
		this.weight = weight;
	}

	public Fruit(String color) {
		super();
		this.color = color;
	}

	@Override
	public String toString() {
		return String.format("Fruit [weight=%s, color=%s]", weight, color);
	}

	public Fruit() {
		super();
	}
	
	

}
