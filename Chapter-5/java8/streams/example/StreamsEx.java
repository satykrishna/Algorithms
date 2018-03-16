package java8.streams.example;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;
import static java.lang.System.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;

import example.input.streams.InputData;
import example.model.streams.Dish;
import example.model.streams.Trader;
import example.model.streams.Transaction;

public class StreamsEx {

	private static Logger logger = Logger.getLogger(StreamsEx.class);

	public static void streamsFlatMappingEx() {
		List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> list2 = Arrays.asList(6, 7, 8, 9);
		List<Integer> squares = list1.stream().map(i -> i * i).collect(toList());
		List<Integer[]> comb = list1.stream().flatMap(i -> list2.stream().map(j -> new Integer[] { i, j })).collect(toList());
		List<Integer[]> divisibleBy3 = list1.stream().flatMap(i -> list2.stream().filter(j -> (i + j) / 3 == 0).map(j -> new Integer[] { i, j }))
				.collect(toList());
		List<Dish> menu = InputData.getMenu();
		menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(dish -> dish.getCaloricLevel());
		Optional<Integer> divisibleBy3Val = list1.stream().filter(x -> x * x / 3 == 0).findFirst();
	}

	public static void reduce() {

		List<Integer> list1 = Arrays.asList(1, 2, 5, 7, 8, 0, -1, -2);

		int sum = list1.stream().reduce(0, Integer::sum);

		Integer optionalSum = list1.stream().reduce(0, Integer::sum);

		Optional<Integer> sum1 = list1.stream().reduce(Integer::sum);

		int size = list1.stream().map(element -> 1).reduce(0, Integer::sum);

		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");

		List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300), new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));

		List<Transaction> ty2011 = transactions.stream().filter(transaction -> transaction.getYear() == 2011)
				.sorted((t1, t2) -> t1.getValue() >= t2.getValue() ? 1 : 0).collect(toList());

		ty2011 = transactions.stream().filter(transaction -> transaction.getYear() == 2011).sorted(comparing(Transaction::getValue)).collect(toList());

		List<String> cities = transactions.stream().map(Transaction::getTrader).map(Trader::getCity).distinct().collect(toList());

		List<Trader> traders = transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge")).map(Transaction::getTrader)
				.sorted(comparing(Trader::getName)).collect(toList());

		String traderNames = transactions.stream().map(Transaction::getTrader).map(Trader::getName).reduce(" ", (name1, name2) -> name1 + " " + name2);

		boolean areTradersInMilan = transactions.stream().map(Transaction::getTrader).map(Trader::getCity).filter(city -> city.equalsIgnoreCase("MILAN"))
				.findAny().isPresent();

		transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("cambridge")).map(Transaction::getValue)
				.forEach(System.out::println);

		int max = transactions.stream().max(comparing(Transaction::getValue)).get().getValue();

		Transaction smallest = transactions.stream().min(comparing(Transaction::getValue)).get();

		int calories = InputData.getMenu().stream().map(Dish::getCalories).reduce(0, Integer::sum);

	}

	public void numericStreams() {

		List<Dish> menu = InputData.getMenu();

		menu.stream().mapToInt(Dish::getCalories).sum();

		OptionalInt maxCalories = menu.stream().mapToInt(Dish::getCalories).max();

		OptionalInt minCalories = menu.stream().mapToInt(Dish::getCalories).min();

		int maxCal = maxCalories.orElse(3);

		int minCal = minCalories.orElse(Integer.min(3, 4));
	}

	public void numericRanges() {

		IntStream evenNos = IntStream.range(1, 100).filter(n -> n % 2 == 0);

		System.out.println(evenNos.count());

		List<int[]> pythogoreanTuple = IntStream.rangeClosed(1, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100)
				.filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).boxed().map(b -> new int[] { a, b, (int) Math.sqrt(a * a + b * b) })).collect(toList());

		IntStream.rangeClosed(1, 100).boxed()
				.flatMap(a -> IntStream.rangeClosed(a, 100).mapToObj(b -> new int[] { a, b, a * a + b * b }).filter(t -> (int) Math.sqrt(t[2]) % 1 == 0));

		Stream.iterate(new int[] { 0, 1 }, t -> new int[] { t[0] + t[1] }).limit(10).forEach(logger::info);

		Stream.generate(new Supplier<Integer>() {

			private int a = 0;
			private int b = 1;

			public Integer get() {
				int c = a + b;
				a = b;
				b = c;
				return c;
			};

		}).limit(10).forEach(out::print);

	}

	public static void generateFibonacci(int limitVal) {
		
		String str = Stream.generate(new Supplier<Integer>() {

			private int a = 0;
			private int b = 1;

			public Integer get() {
				int c = a + b;
				a = b;
				b = c;
				return c;
			};

		}).limit(limitVal).map(e->e.toString()).reduce("", (a, b) ->  a + " " + b);
		
		System.out.println(str);
		
		
		Stream.iterate(new int[]{0,  1}, t -> new int[]{t[1], t[1]+t[0]})
		      .limit(limitVal).forEach(t-> out.println(t[1]));
		
		
		Integer[] arr = new Integer[] {23, 35, 343, 64, 5, 645 };
		
		Stream<Integer> stream = Arrays.stream(arr);
		
		
		 List<String> words = Arrays.asList("Hello", "World");
		 
		 
		 words.stream().flatMap(s->Arrays.stream(s.split(" "))).distinct();
		 
		 
		
		
	}
	
	
	public static void main(String[] args) {
		
		generateFibonacci(11);
	}

}
