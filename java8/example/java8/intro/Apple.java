package example.java8.intro;

public class Apple {

	private int weight;

	private String color;

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Apple(int weight, String color) {
		super();
		this.weight = weight;
		this.color = color;
	}

	public Apple() {
		super();
	}

}
