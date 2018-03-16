package java8.lamba.model;

public class Apple extends Fruit {

	public Apple() {
		super();
	}
	
	public Apple(Integer weight) {
		super(weight);
	}

	public Apple(Integer weight, String color) {
		super(weight, color);
	}

	@Override
	public String toString() {
		return String.format("Apple [color=%s, weight=%s]", color, weight);
	}

	@Override
	public String getColor() {
		return super.getColor();
	}

	@Override
	public void setColor(String color) {
		super.setColor(color);
	}

	public Integer getWeight() {
		return super.getWeight();
	}

	public void setWeight(Integer weight) {
		super.setWeight(weight);
	}

}
