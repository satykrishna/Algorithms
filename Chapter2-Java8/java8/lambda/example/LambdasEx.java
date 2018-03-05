package java8.lambda.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntBiFunction;

import example.java8.intro.Apple;
import java8.lamba.model.Fruit;

public class LambdasEx {

	private static int VAL = 5;

	public static String processFile(FileProcessor fileProcessor) {

		try (BufferedReader reader = new BufferedReader(new FileReader("/home/satya/Desktop/allbreakpoints.bkpt"))) {
			return fileProcessor.process(reader);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

	public static void processingFileWithoutUsingLambdas() {
		FileProcessor processor = new FileProcessor() {

			@Override
			public String process(BufferedReader bufferedReader) {
				try {
					return bufferedReader.readLine();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		};

		String output = processFile(processor);

		System.out.println(output);

	}

	private static void processingFileWithLambdas() {

		FileProcessor fileProcessor = (BufferedReader reader) -> {
			try {
				return reader.readLine() + ", " + reader.readLine();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		};

		processFile(fileProcessor);
	}

	private static void processingFilewithLambdas2() throws Exception {

		processFile((BufferedReader reader) -> {
			try {
				return reader.readLine() + ", This is the second line " + reader.readLine();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		});

	}

	public static <T> void performAction(List<T> list, Consumer<T> consumer) {

		for (T t : list) {
			consumer.accept(t);
		}

	}

	public static <T, R> List<R> map(List<T> list, Function<T, R> function) {

		List<R> output = new ArrayList<R>();

		for (T t : list) {
			R r = function.apply(t);
			output.add(r);
		}

		return output;
	}

	private static void functionalInterfaces() {

		Predicate<Integer> testifIntegerisEven = i -> i % 2 == 0;

		System.out.println(testifIntegerisEven.test(100));

		IntPredicate integerPredicate = i -> i % 2 == 1;

		System.out.println(integerPredicate.test(300));

		Consumer<Apple> consumer = a -> System.out.println(a.getWeight());

		Predicate<Fruit> fruitFilter = f -> f.getColor().equalsIgnoreCase("red");

		Supplier<Apple> appleSupplier = () -> new Apple();

		Function<List<Apple>, Integer> fetchLengthofAppleBox = box -> box.size();

		DoubleBinaryOperator doubleBinaryOperator = (a, b) -> a + b;

		BiFunction<Apple, Apple, Integer> appleBiFunctionComparator = (a, b) -> Math.max(a.getWeight(), b.getWeight());

		Comparator<Apple> appleComparator = (a, b) -> a.getWeight() >= b.getWeight() ? 1 : 0;

		ToIntBiFunction<Apple, Apple> appleToIntBiFunction = (apple1, apple2) -> apple1.getWeight() > apple2.getWeight() ? 1 : -1;

		ToDoubleFunction<Apple> appleFn = a -> (double) a.getWeight();

		Supplier s = () -> new Apple();

		Runnable r = () -> new Apple();

		r = () -> VAL = VAL + 4;

		List<Apple> inventory = new ArrayList<Apple>();

		appleComparator = (a1, a2) -> a1.getWeight() >= a2.getWeight() ? 1 : 0;

		inventory.sort(appleComparator);

		Function<String, Integer> stringToInteger = (st) -> Integer.parseInt(st);

		Function<String, Integer> stringToInteger2 = Integer::parseInt;

		BiPredicate<List<String>, String> contains = (list, element) -> list.contains(element);

		BiPredicate<List<String>, String> contains2 = List::contains;

		List<Integer> weights = Arrays.asList(1, 2, 4, 5, 8);
		

		class MapperToWeights {

			private List<Apple> mapWeights(List<Integer> weights, Function<Integer, Apple> mapper) {

				List<Apple> appleList = new ArrayList<Apple>();
				
				for(Integer i : weights ) {
					appleList.add(mapper.apply(i));
				}
				
				return appleList;
			}
		}

		
		MapperToWeights mapperToWeights = new MapperToWeights();
		
	}

	public static void main(String[] args) throws Exception {

		// processingFileWithoutUsingLambdas();
		//
		// processingFileWithLambdas();
		//
		// processingFilewithLambdas2();

		functionalInterfaces();

	}

}
