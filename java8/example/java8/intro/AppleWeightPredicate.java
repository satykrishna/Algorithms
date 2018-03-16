package example.java8.intro;

public class AppleWeightPredicate implements ApplePredicate {

	private int weight;
	
	public AppleWeightPredicate(int weight) {
		this.weight = weight;
	}
	
	@Override
	public boolean test(Apple apple) {
		return apple.getWeight() > weight;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	
}
