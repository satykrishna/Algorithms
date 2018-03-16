package example.java8.intro;

public class AppleColorPredicate implements ApplePredicate {

	private String color;
	
	 public AppleColorPredicate(String color) {
		 this.color = color;
	}
	
	@Override
	public boolean test(Apple apple) {
		return color.equalsIgnoreCase(apple.getColor());
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
