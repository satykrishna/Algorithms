package example.input.streams;

import static java.lang.System.out;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.IntSupplier;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import example.model.streams.Dish;
import example.model.streams.Trader;
import example.model.streams.Transaction;

@SuppressWarnings("unused")
public class StreamsEx {

	private static Logger logger = Logger.getLogger(StreamsEx.class.getName());

	public static void print(Object obj) {
		logger.info(obj.toString());
	}

	private static void createStreams() {

		Stream<String> stream = Stream.of("satya", "krishna", "kondapalli");

		int totalLength = stream.mapToInt(String::length).sum();

		out.println(totalLength);

		Stream<int[]> emptyStream = Stream.empty();

		int[] array = new int[] { 1, 2, 3, 4, 5, 6 };

		IntStream arrayStream = Arrays.stream(array);

		String[] s = new String[] { "Satya", "krishna" };

		int sum = arrayStream.sum();

		int max = arrayStream.max().orElse(2);

		int min = arrayStream.min().orElse(max);

		arrayStream = arrayStream.boxed().sorted().mapToInt(a -> a);

		long uniqueWords = 0;

		try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
			long uniqueWordsinFile = lines.flatMap(line -> Arrays.stream(line.split(""))).distinct().count();
			long anotherWay = lines.map(eachLine -> Arrays.stream(eachLine.split("")).distinct().count())
					.reduce(Long::sum).get();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// infinite Streams

		Stream.iterate(new int[] { 0, 1 }, t -> new int[] { t[0] + t[1] }).limit(20).map(t -> t[0])
				.forEach(out::println);

		Stream.generate(Math::random).limit(5).forEach(out::println);

		IntStream.generate(new IntSupplier() {

			private int prev = 0;

			private int curr = 1;

			@Override
			public int getAsInt() {
				int oldPrev = this.prev;
				int nextVal = oldPrev + this.curr;
				this.prev = this.curr;
				this.curr = nextVal;
				return oldPrev;
			}
		}).limit(10).forEach(out::println);
	}

	private static void primitiveStreams() {
		List<Dish> menu = InputData.getMenu();
		List<Integer> numbers = InputData.getNums();

		int noofCaloriesinMenu = menu.stream().mapToInt(Dish::getCalories).sum();

		IntStream intStream = menu.stream().mapToInt(Dish::getCalories);

		Stream<Integer> stream = intStream.boxed();

		OptionalInt maxCalories = menu.stream().mapToInt(Dish::getCalories).max();

		int max = maxCalories.orElse(2);

		IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter(number -> number % 2 == 0);

		long count = evenNumbers.count();

		// pythogorean triplets

		IntStream.rangeClosed(1, 100).boxed()
				.flatMap(a -> IntStream.rangeClosed(a, 100).filter(b -> (int) Math.sqrt(a * a + b * b) % 1 == 0).boxed()
						.map(b -> new int[] { a, b, a * a + b * b }));

		// efficient way

		IntStream.rangeClosed(1, 100).boxed()
				.flatMap(a -> IntStream.rangeClosed(a, 100).boxed()
						.map(b -> new int[] { a, b, (int) Math.sqrt(a * a + b * b) })
						.filter(eachPair -> eachPair[2] % 1 == 0))
				.collect(toList());

		// generate the inner stream first.

		/*
		 * int a = 50;
		 * 
		 * Stream<int[]> innerStream = IntStream.rangeClosed(a, 100) .boxed()
		 * .map(b->new int[] {a, b, (int)Math.sqrt(a*a+b*b)})
		 * .filter(pair->pair[2]%1==0);
		 */
		List<int[]> pythogoreanTriplets = IntStream.rangeClosed(1, 100).boxed().flatMap(a -> {
			Stream<int[]> temporary = IntStream.rangeClosed(a, 100).boxed()
					.map(b -> new int[] { a, b, (int) Math.sqrt(a * a + b * b) }).filter(pair -> pair[2] % 1 == 0);
			return temporary;
		}).collect(toList());

		List<int[]> first5Triplets = pythogoreanTriplets.stream().limit(5).skip(1).collect(toList());

	}

	private static void reduceStreamOps() {
		List<Dish> menu = InputData.getMenu();

		List<Integer> numbers = InputData.getNums();

		int sum = numbers.stream().reduce(0, Integer::sum);

		int multiply = numbers.stream().reduce(0, (a, b) -> a * b);

		Optional<Integer> optionalSum = numbers.stream().reduce(Integer::sum);
		Optional<Integer> max = numbers.stream().reduce(Integer::max);

		int count = menu.stream().map(dish -> 1).reduce(0, Integer::sum);

		// alternative
		count = (int) menu.stream().count();

		Stream<Transaction> txStream = InputData.getTransactions().stream();

		List<Transaction> transactions = txStream.filter(tx -> tx.getYear() == 2011).sorted((tx1, tx2) -> {
			if (tx1.getValue() <= tx2.getValue())
				return 1;
			else
				return 0;
		}).collect(toList());

		List<String> uniqueCities = txStream.map(tx -> tx.getTrader().getCity()).distinct().collect(toList());

		List<Trader> tradersFromCambridge = txStream
				.filter(tx -> tx.getTrader().getCity().equalsIgnoreCase("cambridge")).map(tx -> tx.getTrader())
				.sorted((t1, t2) -> t1.getName().compareTo(t2.getName())).collect(toList());

		String str = txStream.map(tx -> tx.getTrader().getName()).distinct().sorted().reduce("",
				(name1, name2) -> name1 + "," + name2);

		Optional<Trader> tradersInMilan = txStream.map(Transaction::getTrader)
				.filter(trader -> trader.getCity().equalsIgnoreCase("milan")).findAny();

		if (tradersInMilan.isPresent()) {
			print(tradersInMilan.get());
		}

		txStream.filter(tx -> tx.getTrader().getName().equalsIgnoreCase("Cambridge")).map(Transaction::getValue)
				.forEach(out::println);

		Optional<Integer> maxValueofTx = txStream.map(Transaction::getValue).reduce(Integer::max);

		Optional<Integer> minValueofTx = txStream.map(Transaction::getValue).reduce(Integer::min);

	}

	private static void introToStreams() {

		List<Dish> menu = InputData.getMenu();

		List<String> threeHighCalories = menu.stream().filter(dish -> dish.getCalories() > 300).map(Dish::getName)
				.limit(3).collect(toList());

		List<Dish> vegetarianDishes = menu.stream().filter(Dish::isVegetarian).collect(toList());

		InputData.getNums().stream().filter(eachNo -> eachNo % 2 == 0).distinct().forEach(System.out::println);

		InputData.getNums().stream().filter(eachNo -> eachNo % 2 == 0).distinct().limit(1).forEach(System.out::println);

		print(threeHighCalories);

		menu.stream().filter(dish -> dish.getCalories() > 300).skip(2).forEach(out::println);

		List<Dish> meatDishes = menu.stream().filter(dish -> dish.getType() == Dish.Type.MEAT).limit(2)
				.collect(toList());

		print(meatDishes);

		List<String> dishNames = menu.stream().map(Dish::getName).collect(toList());

		List<Integer> dishLengths = menu.stream().map(dish -> dish.getName().length()).collect(toList());

		dishLengths = menu.stream().map(Dish::getName).map(String::length).collect(toList());

		InputData.getWords().stream().map(eachWord -> eachWord.split("")).distinct().collect(toList());

		List<String> list = InputData.getWords().stream().map(eachWord -> eachWord.split("")).flatMap(Arrays::stream)
				.distinct().filter(word -> word.trim().length() != 0).collect(toList());

		out.println(list);

		List<Integer> square = InputData.getNums().stream().map(number -> number * number).collect(toList());

		List<Integer> num1List = InputData.getNums();

		List<Integer> num2List = InputData.getNums();

		/*
		 * List<int[]> num3List = num1List.stream().flatMap(eachNoinList1 -> {
		 * Stream<int[]> returnList = num2List.stream().map(eachNoinList2-> new
		 * int[] {eachNoinList1, eachNoinList2}); return returnList;
		 * }).collect(toList());
		 */

		List<int[]> num3List = num1List.stream().flatMap(eachNoinList1 -> {
			return num2List.stream().map(eachNoinList2 -> new int[] { eachNoinList1, eachNoinList2 });
		}).collect(toList());

		List<int[]> num4List = num1List.stream().flatMap(eachNoinList1 -> {
			Stream<int[]> outputStream = num2List.stream()
					.filter(eachNoinList2 -> (eachNoinList1 + eachNoinList2) % 3 == 0)
					.map(eachNoinList2 -> new int[] { eachNoinList1, eachNoinList2 });
			return outputStream;
		}).collect(toList());

		print(num4List);

		if (menu.stream().anyMatch(Dish::isVegetarian)) {
			print("Dish is some what vegetarian friendly");
		}

		boolean isHealthy = menu.stream().allMatch(dish -> dish.getCalories() < 100);

		isHealthy = menu.stream().noneMatch(dish -> dish.getCalories() > 100);

		Optional<Dish> isVegDishExists = menu.stream().findAny();

		if (isVegDishExists.isPresent()) {
			print(isVegDishExists.get());
		}

		menu.stream().filter(Dish::isVegetarian).findAny().map(Dish::getName).ifPresent(logger::info);
	}

	public static void main(String[] args) {

		// introToStreams();

		// Reduce operations
		// reduceStreamOps();

		// Streams->IntStream and boxed()
		// primitiveStreams();

		// create streams from different sources
		createStreams();

	}

}
