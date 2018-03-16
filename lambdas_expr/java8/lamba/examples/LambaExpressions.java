package java8.lamba.examples;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Supplier;

import example.java8.intro.BufferedReaderProcessor;
import java8.lamba.model.Apple;
import java8.lamba.model.Bananna;
import java8.lamba.model.Fruit;
import java8.lamba.model.Orange;

@SuppressWarnings("unused")
public class LambaExpressions {

	private static String FILE = "/home/satya/Desktop/advJava/algos/algorithms/lambdaExpressions/java8"
			+ "/lambda/functionalInterface/BufferedReaderProcessor.java";

	private static List<Apple> inventory = new ArrayList<Apple>();

	static {
		Apple apple1 = new Apple(140, "green");
		Apple apple2 = new Apple(150, "red");
		Apple apple3 = new Apple(120, "green");
		Apple apple4 = new Apple(180, "yellow");
		inventory.add(apple1);
		inventory.add(apple2);
		inventory.add(apple3);
		inventory.add(apple4);
	}

	public static void process(Runnable r) {
		r.run();
	}

	public static String readFromFile(BufferedReaderProcessor processor) throws Exception {
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(FILE)))) {
			return processor.process(reader);
		}
	}

	public static List<Apple> map(List<Integer> list, Function<Integer, Apple> f) {
		List<Apple> appleList = new ArrayList<Apple>();
		for (Integer x : list) {
			appleList.add(f.apply(x));
		}
		return appleList;
	}
	
	static Map<String, Function<Integer, Fruit>> map = new HashMap<String, Function<Integer, Fruit>>();

	static {
		map.put("apple", Apple::new);
		map.put("orange", Orange::new);
		map.put("bananna", Bananna::new);
	}
	
	public static Fruit giveMeFruit(String fruitName, int weight) {
		return map.get(fruitName.toLowerCase()).apply(weight);
	}


	public static void main(String[] args) throws Exception {
		Comparator<Apple> byWeight = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());
		Runnable r1 = () -> System.out.println("This is a runnable lambda expression");
		process(() -> out.println("This is a runnable Java8 Lambda expression"));
		String oneLine = readFromFile(br -> br.readLine());
		String twoLines = readFromFile(br2 -> br2.readLine() + br2.readLine());
		out.print(oneLine);
		out.println(twoLines);
		List<String> stringList = Arrays.asList("Satya", "Krishna", "Kondapalli");
		List<String> filterStringList = InbuiltLambdasEx.filter(stringList, s -> s.length() < 6);
		InbuiltLambdasEx.forEach(stringList, s -> System.out.println(s + " myNames"));
		InbuiltLambdasEx.forEach(Arrays.asList(1, 2, 3, 4, 5), i -> System.out.println(i + 2));
		out.println(InbuiltLambdasEx.map(stringList, s -> s.length()));
		out.println(InbuiltLambdasEx.map(Arrays.asList(1, 2, 3, 4), i -> i + "Satya2"));
		IntPredicate evenintPredicate = i -> i % 2 == 0;
		IntPredicate oddintpredicate = i -> i % 2 == 1;
		Function<BufferedReader, String> f = (br) -> {
			try {
				return br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		};
		stringList.sort((s1, s2) -> s1.compareTo(s2));
		stringList.sort(String::compareTo);
		Function<String, Integer> func1 = s -> Integer.parseInt(s);
		func1 = Integer::parseInt;
		BiPredicate<List<String>, String> contains = (list, element) -> list.contains(element);
		contains = List::contains;
		Supplier<Apple> appleSupplier = () -> new Apple(18, "GREEN");
		Apple green = appleSupplier.get();
		appleSupplier = Apple::new;
		BiFunction<Integer, String, Apple> newApples = (x, c) -> new Apple(x, c);
		newApples = Apple::new;
		Apple a = newApples.apply(10, "YELLOW");
		out.println(map(Arrays.asList(1, 2, 3, 4, 5, 6), (weight) -> new Apple(weight)));
		out.println(map(Arrays.asList(1, 2, 3, 4, 5, 6), Apple::new));
		out.println(giveMeFruit("Bananna", 020));
	}

	
}



@FunctionalInterface

interface TriFunction<A, B, C, D> {
	
	public abstract D returnSomeThing(A a, B b, C c);
	
}