package example.java8.intro;

public class AppleWeightFormat implements AppleFormat {

	@Override
	public String format(Apple apple) {
		if(apple.getWeight() >= 150) {
			return "Heavy Apple";
		}
		return "Apple";
	}

}
