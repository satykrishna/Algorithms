package examples.streams.java8;

import static java.lang.System.out;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.BinaryOperator;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import model.streams.java8.Dish;
import model.streams.java8.Trader;
import model.streams.java8.Transaction;

public class StreamsEx {

	private static List<Dish> menu;

	static {
		menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT), new Dish("beef", false, 700, Dish.Type.MEAT),
				new Dish("chicken", false, 400, Dish.Type.MEAT), new Dish("french fries", true, 530, Dish.Type.OTHER),
				new Dish("rice", true, 350, Dish.Type.OTHER), new Dish("season fruit", true, 120, Dish.Type.OTHER),
				new Dish("pizza", true, 550, Dish.Type.OTHER), new Dish("prawns", false, 300, Dish.Type.FISH),
				new Dish("salmon", false, 450, Dish.Type.FISH));
	}

	public static void pythogoreanTriplets(int start, int end, int limit) {

		IntStream.rangeClosed(start, end).boxed().flatMap(a -> IntStream.rangeClosed(a, end)
				.mapToObj(b -> new double[] { a, b, (int) Math.sqrt(a * a + b * b) }).filter(t -> t[2] % 1 == 0))
				.limit(limit).forEach(arr -> out.println(arr[0] + "...." + arr[1] + "..." + arr[2]));
	}

	
	public static void main(String[] args) {

		menu.stream().filter(Dish::isVegetarian).forEach(out::println);

		List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
		numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);

		menu.stream().filter(d -> d.getType() == Dish.Type.MEAT).limit(2).forEach(out::println);

		List<String> dishNames = menu.stream().map(Dish::getName).collect(toList());

		out.println(dishNames);

		List<Integer> dishLength = menu.stream().map(Dish::getName).map(String::length).collect(toList());

		out.println(dishLength);

		List<String> words = Arrays.asList("Hello", "World", "Good Bye");

		// will not give output of unique characters in the total list
		out.println(words.stream().map(eachString -> eachString.split("")).distinct().collect(toList()));

		// will not give output of unique characters in the total list
		out.println(words.stream().map(str -> str.split("")).map(Arrays::stream).distinct().collect(toList()));

		// will give required output
		out.println(words.stream().map(word -> word.split("")).flatMap(Arrays::stream).distinct()
				.filter(word -> word.trim().length() != 0).collect(toList()));

		List<Integer> inputNums = Arrays.asList(1, 2, 3, 4, 5);

		out.println(inputNums.stream().map(num -> num * num).collect(toList()));

		List<Integer> list1 = Arrays.asList(1, 2, 3);

		List<Integer> list2 = Arrays.asList(4, 5);

		list1.stream().flatMap(i -> (list2.stream().map(j -> new int[] { i, j }))).collect(toList());

		list1.stream().flatMap(i -> (list2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[] { i, j })))
				.collect(toList());

		if (menu.stream().anyMatch(Dish::isVegetarian)) {
			System.out.println("Menu has some vegetarian food");
		}

		if (menu.stream().allMatch(d -> d.getCalories() < 100)) {
			System.out.println("Menu is healthy");
		}

		if (menu.stream().noneMatch(d -> d.getCalories() < 100)) {
			System.out.println("Menu is not healthy");
		}

		Optional<Dish> optionalValue = menu.stream().filter(Dish::isVegetarian).findAny();

		if (optionalValue.isPresent()) {
			System.out.println(optionalValue.get());
		}

		menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(out::println);

		int sum = numbers.stream().reduce(0, Integer::sum);

		Optional<Integer> sub = numbers.stream().reduce((a, b) -> a - b);

		Optional<Integer> max = numbers.stream().reduce(Integer::max);

		Optional<Integer> min = numbers.stream().reduce(Integer::min);

		int count = menu.stream().map(eachDish -> 1).reduce(0, Integer::sum);

		long totalDishes = menu.stream().count();

		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");

		List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));

		List<Transaction> transactionsin2011 = transactions.stream()
				.filter(transaction -> transaction.getYear() == 2011).sorted(comparing(Transaction::getValue))
				.collect(toList());

		out.println(transactionsin2011);

		List<String> cities = transactions.stream().map(transaction -> transaction.getTrader().getCity()).distinct()
				.collect(toList());

		out.println(cities);

		List<Trader> tradersFromCambridge = transactions.stream().map(Transaction::getTrader)
				.filter(t -> t.getCity().equalsIgnoreCase("cambridge")).distinct().sorted(comparing(Trader::getName))
				.collect(toList());

		out.println(tradersFromCambridge);

		List<String> allTraders = transactions.stream().map(Transaction::getTrader).distinct()
				.sorted(comparing(Trader::getName)).map(Trader::getName).collect(toList());

		out.println(allTraders);

		transactions.stream().filter(t -> t.getTrader().getCity().equalsIgnoreCase("milan")).findAny()
				.ifPresent(out::println);
		;

		transactions.stream().filter(t -> t.getTrader().getCity().equalsIgnoreCase("cambridge"))
				.map(Transaction::getValue).forEach(out::println);

		int maxTransactionValue = transactions.stream().map(Transaction::getValue).reduce(Integer::max).get();

		out.println("-----\n" + maxTransactionValue);

		BinaryOperator<Transaction> findMin = (t1, t2) -> t1.getValue() <= t2.getValue() ? t1 : t2;

		transactions.stream().reduce(findMin).ifPresent(out::println);

		int totalCalories = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);

		totalCalories = menu.stream().mapToInt(Dish::getCalories).sum();

		IntStream intStream = menu.stream().mapToInt(Dish::getCalories);

		Stream<Integer> stream = intStream.boxed();

		OptionalInt maxCaloriesinMenu = menu.stream().mapToInt(Dish::getCalories).max();
		int maxCalinMenu = maxCaloriesinMenu.orElse(1);

		IntStream evenNumbers = IntStream.rangeClosed(1, 1000).filter(i -> i % 2 == 0);

		pythogoreanTriplets(1, 1000, 2);

		// build streams

		Stream<String> streams = Stream.of("java8", "Lambdas", "in", "Action", "pdf");

		streams.map(String::toUpperCase).forEach(out::println);

		int[] numbersarray = { 2, 3, 4, 5, 9, 11, 12, 13 };

		int sumofNos = Arrays.stream(numbersarray).sum();

		long uniqueWords = 0;

		try {
			Stream<String> lines = Files.lines(Paths.get("/home/satya/Desktop/advJava/algos/algorithms/streams/examples/streams/java8/StreamsEx.java"));

			 lines.flatMap(line -> Arrays.stream(line.split(""))).distinct().forEach(out::print);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		streamGenerator();
	}


	private static void streamGenerator() {
		Stream.iterate(0, n->n+2).limit(10).forEach(out::println);
		
		Stream.iterate(new int[]{0,1}, t->new int[]{t[1], t[0]+t[1]})
		.limit(10).map(t->t[0]).forEach(out::print);
		
		
		IntSupplier supplier = new IntSupplier() {
			
			private int previous = 0;
			
			private int current = 1;
			
			@Override
			public int getAsInt() {
				int oldPrevious  = this.previous;
				int next = this.previous+current;
				this.previous = current;
				this.current = next;
				return oldPrevious;
			}
		};
		
		IntStream.generate(supplier).limit(10).forEach(out::println);
	}

}
