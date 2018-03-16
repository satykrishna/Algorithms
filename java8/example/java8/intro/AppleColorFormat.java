package example.java8.intro;

public class AppleColorFormat implements AppleFormat {

	@Override
	public String format(Apple apple) {
		if(apple.getColor().equalsIgnoreCase("Green")) {
			return "Green Apple " ;
		}
		return "Apple";
	}

}
