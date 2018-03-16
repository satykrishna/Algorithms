package example.java8.intro;

import java.util.ArrayList;
import java.util.List;

public class TestApple {
	
	public static void formatApples(List<Apple> inventory, AppleFormat format) {
		for(Apple each : inventory) {
			System.out.println(format.format(each));
		}
	}

	public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate applePredicate) {
		List<Apple> result = new ArrayList<Apple>();
		for (Apple apple : inventory) {
			if (applePredicate.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}

	public static void main(String[] args) {

		
		Apple apple1 = new Apple(140, "green");
		Apple apple2 = new Apple(150, "red");
		Apple apple3 = new Apple(120, "green");
		Apple apple4 = new Apple(180, "yellow");
		
		
		List<Apple> inventory = new ArrayList<Apple>();

		inventory.add(apple1);
		inventory.add(apple2);
		inventory.add(apple3);
		inventory.add(apple4);

		List<Apple> greenApples = filterApples(inventory, new AppleColorPredicate("green"));

		List<Apple> heavyApples = filterApples(inventory, new AppleWeightPredicate(200));

		List<Apple> heavyGreenApples = filterApples(inventory, new AppleColorAndWeightPredicate(120, "green"));
	}

}
