package java8.lamba.model;

public class Bananna extends Fruit {

	private static final long serialVersionUID = 1476598860317825583L;

	public Bananna() {
		super();
	}

	public Bananna(Integer weight) {
		super(weight);
	}

	public Bananna(Integer weight, String color) {
		super(weight, color);
	}

	@Override
	public String toString() {
		return String.format("Bananna [color=%s, weight=%s]", color, weight);
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
