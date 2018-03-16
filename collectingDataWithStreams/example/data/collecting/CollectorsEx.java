package example.data.collecting;

import static java.lang.System.out;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import example.data.collecting.CollectorsEx.CaloricLevel;
import example.input.streams.InputData;
import example.model.streams.Dish;
import example.model.streams.Dish.Type;

@SuppressWarnings("unused")
public class CollectorsEx {

	public static void main(String[] args) {
		// reduction();
		// grouping();
		// partitioning();
//		summaryofCollectors();
		out.println(partitionWithPrimesUsingCollector(10));
	}

	private static void summaryofCollectors() {
		List<Dish> menu = InputData.getMenu();

		List<Dish> dishes = menu.stream().collect(toList());

		Set<Dish> dishSet = menu.stream().collect(toSet());

		Collection<Dish> dishCollection = menu.stream().collect(toCollection(ArrayList::new));

		long howManyDishes = menu.stream().count();

		int totalCals = menu.stream().collect(summingInt(Dish::getCalories));

		double avgCals = menu.stream().collect(averagingInt(Dish::getCalories));

		IntSummaryStatistics stats = menu.stream().collect(summarizingInt(Dish::getCalories));

		String shortMenu = menu.stream().map(Dish::getName).collect(joining(","));

		Optional<Dish> fatDish = menu.stream().collect(maxBy(comparing(Dish::getCalories)));

		Optional<Dish> lightDish = menu.stream().collect(minBy(comparingInt(Dish::getCalories)));

		shortMenu = menu.stream().collect(reducing("", Dish::getName, (a, b) -> a + " " + b));

		totalCals = menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));

		howManyDishes = (long) menu.stream().collect(collectingAndThen(toSet(), Set::size));

		Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType, toList()));

		Map<Boolean, List<Dish>> partitionByVeggies = menu.stream().collect(partitioningBy(Dish::isVegetarian));

		out.println(shortMenu);
	}

	private static void partitioning() {
		List<Dish> menu = InputData.getMenu();
		Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(partitioningBy(Dish::isVegetarian));

		List<Dish> veggies = partitionedMenu.get(true);

		veggies = menu.stream().filter(Dish::isVegetarian).collect(toList());

		Map<Boolean, Map<Dish.Type, List<Dish>>> veggieDishesByType = menu.stream()
				.collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));

		Map<Boolean, Dish> mostCaloricDishPartitionedByVeggie = menu.stream().collect(partitioningBy(Dish::isVegetarian,
				collectingAndThen(maxBy(comparing(Dish::getCalories)), Optional::get)));

		Map<Boolean, Map<Boolean, List<Dish>>> dishesWithGreaterThan500 = menu.stream()
				.collect(partitioningBy(Dish::isVegetarian, partitioningBy(dish -> dish.getCalories() >= 500)));

		Map<Boolean, Long> dishesWithVeggies = menu.stream().collect(partitioningBy(Dish::isVegetarian, counting()));

	}

	private Map<Boolean, List<Integer>> partitionByPrime_old(int n) {
		return IntStream.rangeClosed(2, n).boxed().collect(partitioningBy(each -> isPrime_old(each)));
	}

	private boolean isPrime_old(int candidate) {
		return IntStream.rangeClosed(2, (int) Math.sqrt((double) candidate)).noneMatch(i -> candidate % i == 0);
	}

	public enum CaloricLevel {
		DIET, NORMAL, FAT
	}

	private static void grouping() {
		List<Dish> menu = InputData.getMenu();
		Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
		out.println(dishesByType);

		Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(groupingBy(dish -> {
			if (dish.getCalories() <= 400)
				return CaloricLevel.DIET;
			else if (dish.getCalories() <= 700)
				return CaloricLevel.NORMAL;
			else
				return CaloricLevel.FAT;
		}));

		Map<CaloricLevel, List<Dish>> dByC = menu.stream().collect(groupingBy(dish -> {
			if (dish.getCalories() <= 400)
				return CaloricLevel.DIET;
			else if (dish.getCalories() <= 700)
				return CaloricLevel.NORMAL;
			else
				return CaloricLevel.FAT;
		}));

		Map<Dish.Type, Map<CaloricLevel, List<Dish>>> multiGdByC = menu.stream()
				.collect(groupingBy(Dish::getType, groupingBy(dish -> {
					if (dish.getCalories() <= 400)
						return CaloricLevel.DIET;
					else if (dish.getCalories() <= 700)
						return CaloricLevel.NORMAL;
					else
						return CaloricLevel.FAT;
				})));

		Map<Dish.Type, Integer> totalDishesCaloriesByType = menu.stream()
				.collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));

		Map<Dish.Type, Integer> noofDishesByType = menu.stream()
				.collect(groupingBy(Dish::getType, reducing(0, dish -> 1, Integer::sum)));

		out.println(totalDishesCaloriesByType);
		out.println(noofDishesByType);

		Map<Type, Optional<Dish>> maxCaloriesforaGivenDishType = menu.stream()
				.collect(groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));

		Map<Dish.Type, Dish> minCaloriesforTheGivenDishType = menu.stream().collect(
				groupingBy(Dish::getType, collectingAndThen(minBy(comparing(Dish::getCalories)), Optional::get)));

		out.print(minCaloriesforTheGivenDishType);

		Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream()
				.collect(groupingBy(Dish::getType, mapping(dish -> {
					if (dish.getCalories() <= 400)
						return CaloricLevel.DIET;
					else if (dish.getCalories() <= 700)
						return CaloricLevel.NORMAL;
					else
						return CaloricLevel.FAT;
				}, toSet())));

		Map<Type, TreeSet<CaloricLevel>> calsByType = menu.stream().collect(groupingBy(Dish::getType, mapping(dish -> {
			if (dish.getCalories() <= 400)
				return CaloricLevel.DIET;
			else if (dish.getCalories() <= 700)
				return CaloricLevel.NORMAL;
			else
				return CaloricLevel.FAT;
		}, toCollection(TreeSet::new))));

	}

	private static void reduction() {
		List<Dish> menu = InputData.getMenu();
		// using collectors
		long howManyDishes = menu.stream().collect(counting());
		// using reduce operation
		howManyDishes = menu.stream().mapToInt(dish -> 1).reduce(0, Integer::sum);
		// using count method
		howManyDishes = menu.stream().count();

		// find max and min number of calories in menu
		// by converting it to IntStream and fetch max and min
		int maxCalories = menu.stream().mapToInt(Dish::getCalories).max().getAsInt();
		int minCalories = menu.stream().mapToInt(Dish::getCalories).min().getAsInt();
		// another way: use reduce operation
		Optional<Integer> maxC = menu.stream().map(Dish::getCalories).reduce(Integer::max);
		Optional<Integer> minC = menu.stream().map(Dish::getCalories).reduce(Integer::min);
		// use max end operation
		Comparator<Dish> comparator = Comparator.comparingInt(Dish::getCalories);
		Optional<Dish> mostCalorieDish = menu.stream().max(comparator);
		Optional<Dish> minCalorieDish = menu.stream().min(comparator);
		// use collectors
		mostCalorieDish = menu.stream().collect(maxBy(comparator));
		minCalorieDish = menu.stream().collect(minBy(comparator));

		// find total calories in the Dish
		// use reduction
		long totalCalories = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
		// use mapToInt and IntStream
		IntStream calorieInfo = menu.stream().mapToInt(Dish::getCalories);
		int total = calorieInfo.sum();
		int avg = (int) calorieInfo.average().getAsDouble();
		// use collectors
		totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
		double avgCalories = menu.stream().collect(averagingDouble(Dish::getCalories));
		IntSummaryStatistics stats = menu.stream().collect(summarizingInt(Dish::getCalories));

		// short menu
		// use reduce
		String shortMenu = menu.stream().map(Dish::getName).reduce("", (d1, d2) -> d1 + "," + d2);
		// using collectors
		shortMenu = menu.stream().map(Dish::getName).collect(joining(","));

		// collectors:reduction
		int totalCaloriesUsingReduction = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
		int mostCalorieDishR = menu.stream().collect(reducing(Integer.MIN_VALUE, Dish::getCalories,
				(calorie1, calorie2) -> calorie1 >= calorie2 ? calorie1 : calorie2));
		Optional<Dish> mostCalorieDishReduced = menu.stream()
				.collect(reducing((d1, d2) -> d1.getCalories() >= d2.getCalories() ? d1 : d2));

		// not an efficient way to use streams
		Stream<Integer> stream = Arrays.asList(1, 2, 3, 4).stream();
		List<Integer> numbers = stream.reduce(new ArrayList<Integer>(), (List<Integer> l, Integer e) -> {
			l.add(e);
			return l;
		}, (List<Integer> l1, List<Integer> l2) -> {
			l1.addAll(l2);
			return l1;
		});
		int totalCalories3 = menu.stream().mapToInt(Dish::getCalories).sum();
		Optional<Integer> totalCaloriesinMenu = menu.stream().map(Dish::getCalories).reduce(Integer::sum);

	}

	public static Map<Boolean, List<Integer>> partitionWithPrimesUsingCollector(int n) {
		return IntStream.rangeClosed(2, n).boxed().collect(new PrimeNoCollector());
	}

	public static <A> List<A> takeAWhile(List<A> list, Predicate<A> p) {
		int i = 0;

		for (A item : list) {
			if (!p.test(item)) {
				return list.subList(0, i);
			}
			++i;
		}

		return list;
	}

	public static boolean isPrime(List<Integer> primes, int candidate) {
		int candidateRoot = (int) Math.sqrt(candidate);
		return takeAWhile(primes, eachNo -> eachNo <= candidateRoot).stream()
				.noneMatch(eachNo -> candidate % eachNo == 0);
	}

}
