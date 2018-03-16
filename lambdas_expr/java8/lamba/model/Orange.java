package java8.lamba.model;

public class Orange extends Fruit {

	public Orange(String color, Integer weight) {
		super();
		this.color = color;
		this.weight = weight;
	}

	public Orange(String color) {
		super();
		this.color = color;
	}

	public Orange(Integer weight) {
		super();
		this.weight = weight;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return String.format("Orange [color=%s, weight=%s]", color, weight);
	}

}
