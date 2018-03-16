package java8.lambda.examples;

public class Fruit {

	private String name;
	private String type;
	private String color;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Fruit(String name, String type, String color) {
		super();
		this.name = name;
		this.type = type;
		this.color = color;
	}

	public Fruit() {
		super();
	}

	public Fruit(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	public Fruit(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("Fruit [name=%s, type=%s, color=%s]", name, type, color);
	}

}
