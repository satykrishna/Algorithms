package java8.lambda.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import example.java8.intro.Apple;

import static java.util.Comparator.comparing;

public class MethodReferences {

	public static void methods() {
		List<String> list = Arrays.asList("satya", "krishna", "kondapalli");
		list.sort((String s1, String s2) -> s1.compareTo(s2));
		Comparator<String> defaultOrder = String::compareTo;
		Comparator<String> reverseOrder = defaultOrder.reversed();
		list.sort(reverseOrder);
		Function<String, Integer> function = (String s) -> Integer.parseInt(s);
		function = Integer::parseInt;
		BiPredicate<List<String>, String> biPredicate = (List<String> l, String i) -> l.contains(i);
		biPredicate = List::contains;
	}

	public static void constructors() {
		Supplier<Fruit> fruitSupplier = () -> new Fruit();
		Fruit f1 = fruitSupplier.get();
		Function<String, Fruit> f = (String name) -> new Fruit(name);
		Function<String, Fruit> anotherFruit = Fruit::new;
		f1 = anotherFruit.apply("Apple");
	}

	public static void map(List<String> names, Function<String, Fruit> f) {
		List<Fruit> result = new ArrayList<Fruit>();
		for (String n : names) {
			result.add(f.apply(n));
		}
		System.out.println(result);
	}

	public static void map(List<String> names, BiFunction<String, String, Fruit> f) {
		List<Fruit> result = new ArrayList<Fruit>();
		for (String n : names) {
			result.add(f.apply(n, "ORGANIC"));
		}
		System.out.println(result);
	}

	public static void main(String[] args) {
		Function<String, Fruit> f = Fruit::new;
		List<String> names = Arrays.asList("Apple", "Bannana", "Orange", "StrawBerries");
		map(names, f);
		BiFunction<String, String, Fruit> biFunction = Fruit::new;
		map(names, biFunction);
	}

	private static Map<String, BiFunction<String, String, Fruit>> fruitList = new HashMap<String, BiFunction<String, String, Fruit>>();

	static {
		fruitList.put("Apple", Fruit::new);
		fruitList.put("Orange", Fruit::new);
		fruitList.put("Bananna", Fruit::new);
	}

	public static Fruit getFruit(String name, String type) {
		return fruitList.get(name).apply(name, type);
	}

	private static Map<String, TriFunction<String, String, String, Fruit>> myFruitBasket = new HashMap<String, TriFunction<String, String, String, Fruit>>();

	static {
		fruitList.put("Apple", Fruit::new);
		fruitList.put("Orange", Fruit::new);
		fruitList.put("Bananna", Fruit::new);
	}

	public static Fruit getFruitFromBasket(String name, String type, String color) {
		return myFruitBasket.get(name).create(name, type, color);
	}

	public static void sortApples() {
		List<Apple> fruits = new ArrayList<Apple>();
		fruits.sort((a1, a2) -> a1.getWeight() <= a2.getWeight() ? 1 : -1);
		Comparator<Apple> orderByWeight = comparing(Apple::getWeight);
		fruits.sort(orderByWeight);
		Comparator<Apple> reverseOrderByWeight = orderByWeight.reversed();
		Comparator<Apple> thenCompareByCountry = reverseOrderByWeight.thenComparing(Apple::getColor);
		fruits.sort(thenCompareByCountry);
	}

	public static void composingPredicates() {
		Predicate<Apple> redApple = (a) -> a.getColor().equalsIgnoreCase("Red");
		Predicate<Apple> notRedApple = redApple.negate();
		Predicate<Apple> redAndHeavyApple = redApple.and((a) -> a.getWeight() > 150);
		Predicate<Apple> redHeavyorGreenApples = redAndHeavyApple.or((a) -> a.getColor().equalsIgnoreCase("green"));
	}

	public static void composingFns() {
		Function<Integer, Integer> f = (x) -> {
			System.out.println("f is called "+x);
			return (x + 1);
		};
		Function<Integer, Integer> g = x ->{
			System.out.println("g is called " + x);
			return x*2;
		};
		Function<Integer, Integer> h = f.andThen(g);
		int result = h.apply(1);
		h =  f.compose(g);
		result = h.apply(2);
	}

	static class Letter {
		public static String addHeader(String text) {
			return "From Raul, Mario, Alan : " + text;
		}
		
		public static String addFooter(String text) {
			return text + " Kind Regards";
		}
		
		public static String checkSpelling(String text) {
			return text.replaceAll("labda", "lambda");
		}
		
		public static void main(String[] args) {
			Function<String, String> letterHeader = Letter::addHeader;
			Function<String, String> transform=
					letterHeader.andThen(Letter::checkSpelling)
					.andThen(Letter::addFooter);
			
			System.out.println(transform.apply("Satya"));
		}
	}
}
