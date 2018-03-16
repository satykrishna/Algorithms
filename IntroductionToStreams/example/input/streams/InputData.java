package example.input.streams;

import java.util.Arrays;
import java.util.List;

import example.model.streams.Dish;
import example.model.streams.Trader;
import example.model.streams.Transaction;

public class InputData {

	private static List<Dish> menu;
	
	private static List<String> words;
	
	private static List<Integer> numbers;

	private static List<Transaction> transactions;

	private static void initialize() {
		menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT), new Dish("beef", false, 700, Dish.Type.MEAT),
				new Dish("chicken", false, 400, Dish.Type.MEAT), new Dish("french fries", true, 530, Dish.Type.OTHER),
				new Dish("rice", true, 350, Dish.Type.OTHER), new Dish("season fruit", true, 120, Dish.Type.OTHER),
				new Dish("pizza", true, 550, Dish.Type.OTHER), new Dish("prawns", false, 300, Dish.Type.FISH),
				new Dish("salmon", false, 450, Dish.Type.FISH));

		numbers = Arrays.asList(1, 2, 3, 4, 5);
		words = Arrays.asList("Hello", "world", "satya krishna");

		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");

		transactions = Arrays.asList(new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));
	}

	static {
		initialize();
	}

	public static List<Dish> getMenu() {
		return menu;
	}

	public static List<Integer> getNums() {
		return numbers;
	}

	public static List<String> getWords() {
		return words;
	}

	public static List<Transaction> getTransactions() {
		return transactions;
	}

}
