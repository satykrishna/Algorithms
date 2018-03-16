package model.streams.java8;

public class Dish {

	private String name;
	private boolean vegetarian;
	private int calories;
	private Type type;

	public enum Type {
		MEAT, FISH, OTHER;
		private String name;
		Type() {

		}
		Type(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return super.toString();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public Dish(String name, boolean vegetarian, int calories, Type type) {
		super();
		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
		this.type = type;
	}

	@Override
	public String toString() {
		return String.format("Dish [name=%s, vegetarian=%s, calories=%s, type=%s]", name, vegetarian, calories, type);
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	

}
