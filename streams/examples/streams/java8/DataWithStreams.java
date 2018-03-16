package examples.streams.java8;

import static java.lang.System.out;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Currency;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.streams.java8.Dish;
import model.streams.java8.Transaction;

public class DataWithStreams {

	private static List<Dish> menu;

	public static enum CALORIELEVEL {
		DIET, NORMAL, FAT
	}

	static {
		menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT), new Dish("beef", false, 700, Dish.Type.MEAT),
				new Dish("chicken", false, 400, Dish.Type.MEAT), new Dish("french fries", true, 530, Dish.Type.OTHER),
				new Dish("rice", true, 350, Dish.Type.OTHER), new Dish("season fruit", true, 120, Dish.Type.OTHER),
				new Dish("pizza", true, 550, Dish.Type.OTHER), new Dish("prawns", false, 300, Dish.Type.FISH),
				new Dish("salmon", false, 450, Dish.Type.FISH));
	}

	public static boolean isPrime(int candidate) {
		int candidateRoot = (int) Math.sqrt((double) candidate);
		return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> i % 2 == 0);
	}

	public static Map<Boolean, List<Integer>> partition(int n) {
		return IntStream.rangeClosed(2, n).boxed().collect(partitioningBy(candidate -> isPrime(candidate)));
	}

	private static void dishesSummary() {
		List<Dish> dishList = menu.stream().collect(toList());

		Set<Dish> dishSet = menu.stream().collect(toSet());

		// Collection<Dish> dishes = menu.stream().collect(toCollection(),
		// ArrayList::new);

		long dishCount = menu.stream().count();

		dishCount = menu.stream().collect(counting());

		int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));

		double avgCalories = menu.stream().collect(averagingInt(Dish::getCalories));

		DoubleSummaryStatistics stats = menu.stream().collect(summarizingDouble(Dish::getCalories));

		String shortMenu = menu.stream().map(Dish::getName).collect(joining(","));

		Optional<Dish> fattest = menu.stream().collect(maxBy(comparingInt(Dish::getCalories)));

		Optional<Dish> lightest = menu.stream().collect(minBy(comparingInt(Dish::getCalories)));
		
		int size = menu.stream().collect(collectingAndThen(toList(), List::size));
		
		Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
		
		Map<Boolean, List<Dish>> dishesByVegan = menu.stream().collect(partitioningBy(Dish::isVegetarian));
		
		
	}

	public static void main(String[] args) {

		List<Transaction> transactions = new ArrayList<Transaction>();

		Map<Currency, List<Transaction>> transactionByCurrencies = new HashMap<>();

		for (Transaction transaction : transactions) {
			Currency currency = transaction.getCurrency();
			List<Transaction> transactionsForCurrency = transactionByCurrencies.get(currency);

			if (transactionsForCurrency == null) {
				transactionsForCurrency = new ArrayList<>();
				transactionByCurrencies.put(currency, transactionsForCurrency);
			}

			transactionsForCurrency.add(transaction);
		}

		// equivalent definition of above
		transactionByCurrencies = transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency));

		long howmanyDishes = menu.stream().collect(counting());

		howmanyDishes = menu.stream().count();

		Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);

		Optional<Dish> mostCalorieDish = menu.stream().collect(maxBy(dishCaloriesComparator));

		System.out.println(mostCalorieDish.get());

		int totalCals = menu.stream().collect(summingInt(Dish::getCalories));

		double avgCalories = menu.stream().collect(averagingDouble(Dish::getCalories));

		IntSummaryStatistics menuStats = menu.stream().collect(summarizingInt(Dish::getCalories));

		out.println(menuStats);

		String shortMenu = menu.stream().map(Dish::getName).collect(joining(","));

		out.print(shortMenu);

		totalCals = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);

		totalCals = menu.stream().mapToInt(Dish::getCalories).sum();

		Map<CALORIELEVEL, List<Dish>> dishesByCalorieLevel = menu.stream().collect(groupingBy(dish -> {
			if (dish.getCalories() <= 400) {
				return CALORIELEVEL.DIET;
			} else if (dish.getCalories() <= 700) {
				return CALORIELEVEL.NORMAL;
			} else {
				return CALORIELEVEL.FAT;
			}
		}));

		Map<Dish.Type, Map<CALORIELEVEL, List<Dish>>> dishByTypeCaloricLevel = menu.stream()
				.collect(groupingBy(Dish::getType, groupingBy(dish -> {
					if (dish.getCalories() <= 400) {
						return CALORIELEVEL.DIET;
					} else if (dish.getCalories() <= 700) {
						return CALORIELEVEL.NORMAL;
					} else {
						return CALORIELEVEL.FAT;
					}
				})));

		Map<Dish.Type, Long> typesCount = menu.stream().collect(groupingBy(Dish::getType, counting()));

		Map<Dish.Type, Optional<Dish>> highestCalorieCountByDishType = menu.stream()
				.collect(groupingBy(Dish::getType, maxBy(dishCaloriesComparator)));

		System.out.println(typesCount);
		System.out.println("------------");
		System.out.println(highestCalorieCountByDishType);

		Map<Dish.Type, Dish> mostCaloriesByType = menu.stream()
				.collect(groupingBy(Dish::getType, collectingAndThen(maxBy(dishCaloriesComparator), Optional::get)));

		System.out.println(mostCaloriesByType);

		Map<Dish.Type, Integer> totalCaloriesByType = menu.stream()
				.collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));

		Map<Dish.Type, Set<CALORIELEVEL>> caloricLevelsByType = menu.stream()
				.collect(groupingBy(Dish::getType, mapping(dish -> {
					if (dish.getCalories() <= 400)
						return CALORIELEVEL.DIET;
					else if (dish.getCalories() <= 700)
						return CALORIELEVEL.NORMAL;
					else
						return CALORIELEVEL.FAT;
				}, toSet())));

		Map<Boolean, List<Dish>> partitionMenu = menu.stream().collect(partitioningBy(Dish::isVegetarian));

		List<Dish> veggieDishes = partitionMenu.get(true);

		veggieDishes = menu.stream().collect(partitioningBy(Dish::isVegetarian)).get(true);

		veggieDishes = menu.stream().filter(Dish::isVegetarian).collect(toList());

		Map<Boolean, Map<Dish.Type, List<Dish>>> veggieDishesByType = menu.stream()
				.collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));

		Map<Boolean, Dish> mostCaloriesPartitionedByVegetarian = menu.stream().collect(
				partitioningBy(Dish::isVegetarian, collectingAndThen(maxBy(dishCaloriesComparator), Optional::get)));

		menu.stream().collect(partitioningBy(Dish::isVegetarian, partitioningBy(d -> d.getCalories() > 500)));

		dishesSummary();
	}
	
	

}
