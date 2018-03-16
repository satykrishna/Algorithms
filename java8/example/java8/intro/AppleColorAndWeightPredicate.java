package example.java8.intro;

public class AppleColorAndWeightPredicate implements ApplePredicate {

	private int weight;
	
	private String color;
	
	
	
	public AppleColorAndWeightPredicate(int weight, String color) {
		super();
		this.weight = weight;
		this.color = color;
	}



	@Override
	public boolean test(Apple apple) {
		return color.equalsIgnoreCase(apple.getColor()) && apple.getWeight() > weight;
	}

}
