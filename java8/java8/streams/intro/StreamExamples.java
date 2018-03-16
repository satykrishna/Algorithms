package java8.streams.intro;

import static java.util.stream.Collectors.toList;
import static java.lang.System.out;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamExamples {

	public static List<Dish> getMenu() {
		return Arrays.asList(new Dish("pork", false, 800, Type.MEAT), new Dish("beef", false, 700, Type.MEAT),
				new Dish("chicken", false, 400, Type.MEAT), new Dish("french fries", true, 530, Type.OTHER),
				new Dish("rice", true, 350, Type.OTHER), new Dish("season fruit", true, 120, Type.OTHER),
				new Dish("pizza", true, 550, Type.OTHER), new Dish("prawns", false, 300, Type.FISH),
				new Dish("salmon", false, 450, Type.FISH));
	}

	public static void printD() {
		System.out.println("-----------------");
	}

	public static void main(String[] args) {
		List<Dish> menu = getMenu();
		List<String> names = menu.stream().filter(dish -> dish.getCalories() > 300).map(Dish::getName).limit(3)
				.collect(toList());
		System.out.println(names);
		names = menu.stream().filter(d -> {
			System.out.println("Filtering : " + d.getName());
			return d.getCalories() > 300;
		}).map(d -> {
			System.out.println("Mapping : " + d.getName());
			return d.getName();
		}).limit(3).collect(toList());
		System.out.println(names);
		menu.stream().forEach(System.out::println);
		System.out.println("-----------------");
		List<Dish> vegetarianDishes = menu.stream().filter(d -> d.isVegetarian()).collect(toList());
		vegetarianDishes = menu.stream().filter(Dish::isVegetarian).collect(toList());
		vegetarianDishes.stream().forEach(System.out::println);
		System.out.println("-----------------");
		List<Integer> intList = Arrays.asList(1, 2, 3, 2, 3, 2, 2, 2, 40, 81, 910, 80, 1, 5, 7, 6, 8);

		List<Integer> filterIntList = intList.stream().filter((Integer i) -> i % 2 == 0).distinct().collect(toList());
		filterIntList = intList.stream().filter((Integer i) -> i % 2 == 0).distinct().collect(toList());
		System.out.println(filterIntList);
		System.out.println("-----------------");
		filterIntList = intList.stream().filter((Integer i) -> i % 2 == 0).distinct().skip(1).collect(toList());
		System.out.println(filterIntList);

		Predicate<Dish> vegeDishes = Dish::isVegetarian;

		Predicate<Dish> meatDishes = vegeDishes.negate();

		List<Dish> firstTwoMeatDishes = menu.stream().filter(meatDishes).limit(2).collect(toList());

		System.out.println(firstTwoMeatDishes);

		printD();

		List<String> firstTwoMeatDishnames = menu.stream().filter(meatDishes).limit(2).map(Dish::getName)
				.collect(toList());
		System.out.println(firstTwoMeatDishnames);

		printD();

		List<String> words = Arrays.asList("JAVA8", "Lambdas", "In", "Action");

		Function<String, Integer> wordToLength = String::length;

		List<Integer> wordLengths = words.stream().map(wordToLength).collect(toList());

		out.println(wordLengths);

		printD();

		Function<Dish, Integer> dishToLength = (Dish d) -> d.getName().length();

		List<Integer> dishNameLength = menu.stream().map(dishToLength).collect(toList());

		dishNameLength = menu.stream().map(Dish::getName).map(String::length).collect(toList());

		// out.println(dishNameLength);

		printD();

		words = Arrays.asList("Hello", "World", "Hello");

		List<String[]> l = words.stream().map((each) -> each.split("")).distinct().collect(toList());

		// System.out.println(l);

		List<Stream<String>> lp = words.stream().map(str -> str.split("")).map(Arrays::stream).collect(toList());

		for (String[] arr : l) {
			for (String a : arr) {
				out.println(a);
			}
			printD();
		}

		out.println(lp);

		printD();

		String[] arrayofWords = { "GoodBye", "World" };

		Stream<String> streamofWords = Arrays.stream(arrayofWords);

		List<String> uniqueCharacters = words.stream().map(each -> each.split("")).flatMap(Arrays::stream).distinct()
				.collect(toList());

		System.out.println(uniqueCharacters);

		printD();

		List<Integer> firstList = Arrays.asList(1, 2, 3);
		List<Integer> secondList = Arrays.asList(3, 4);

		List<int[]> pairs = firstList.stream()
				.flatMap(num1 -> secondList.stream().filter(num2->(num1+num2)%3==0).map(num2 -> new int[] { num1, num2 })).collect(toList());
		
		for(int[] eachPair : pairs) {
			System.out.println(eachPair[0]+"..."+eachPair[1]);
		}
	}

}
