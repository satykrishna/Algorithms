package chap6.data.collecting;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import example.input.streams.InputData;
import example.model.streams.Dish;
import example.model.streams.Dish.Type;

public class Chap6Ex {

	private static List<Dish> menu = InputData.getMenu();

	private enum CALORIELEVEL {

		DIET, NORMAL, FAT
	}

	public static void groupingOrMapping() {

		Map<Dish.Type, List<Dish>> groupByDishTypes =

				menu.stream().collect(groupingBy(Dish::getType));

		Map<CALORIELEVEL, List<Dish>> groupDishesByCaloricLevel =

				menu.stream().collect(groupingBy(dish -> {

					if (dish.getCalories() > 400 & dish.getCalories() <= 500)
						return CALORIELEVEL.DIET;

					else if (dish.getCalories() > 500)
						return CALORIELEVEL.FAT;

					else
						return CALORIELEVEL.NORMAL;
				}));

		Map<Dish.Type, Map<CALORIELEVEL, List<Dish>>> groupByCalorieLevelAsWellAsDishType =

				menu.stream().collect(groupingBy(Dish::getType, groupingBy(dish -> {
					if (dish.getCalories() > 600)
						return CALORIELEVEL.FAT;
					else if (dish.getCalories() > 500)
						return CALORIELEVEL.DIET;
					else
						return CALORIELEVEL.NORMAL;
				})));

		Map<Type, Long> groupDishByCount =

				menu.stream().collect(groupingBy(Dish::getType, counting()));

		Map<Type, List<String>> dishByNames =

				menu.stream().collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));

		Map<Dish.Type, Optional<Dish>> dishByTypeWithMenu = menu.stream().collect(groupingBy(Dish::getType, maxBy(comparing(Dish::getCalories))));

		Map<Dish.Type, Dish> dishesWithoutOptions = menu.stream()
				.collect(groupingBy(Dish::getType, collectingAndThen(minBy(comparing(Dish::getCalories)), Optional::get)));

		Map<Type, Long> dWithOutOptional = menu.stream()
				.collect(groupingBy(Dish::getType, collectingAndThen(summarizingInt(Dish::getCalories), IntSummaryStatistics::getSum)));

		Map<Type, IntSummaryStatistics> stats = menu.stream().collect(groupingBy(Dish::getType, summarizingInt(Dish::getCalories)));

		stats.get(Dish.Type.FISH).getAverage();

		Map<Dish.Type, List<String>> dishNamesMap = menu.stream()
				.collect(groupingBy(Dish::getType, mapping(Dish::getName, toCollection(LinkedList<String>::new))));

		Map<Dish.Type, Set<Integer>> dishTypeCalories = menu.stream().collect(groupingBy(Dish::getType, mapping(Dish::getCalories, toSet())));

		Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(partitioningBy(Dish::isVegetarian));

		Map<Boolean, Map<Dish.Type, List<String>>> partitionLevel2Dishes = menu.stream()
				.collect(partitioningBy(dish -> dish.getCalories() > 400, groupingBy(Dish::getType, mapping(Dish::getName, toList()))));

		Map<Boolean, Map<Type, Long>> partitionLevel2Dishess = menu.stream().collect(partitioningBy(dish -> dish.getCalories() > 400,
				groupingBy(Dish::getType, collectingAndThen(summarizingInt(Dish::getCalories), IntSummaryStatistics::getSum))));

		int listOfPrimesUpto = 100;

		Map<Boolean, List<Integer>> listofNos =

				IntStream.rangeClosed(1, listOfPrimesUpto).boxed().collect(partitioningBy(Chap6Ex::isGivenNumberPrime));

		Map<Boolean, List<Integer>> primeNumbersCollectWithData = IntStream.range(2, listOfPrimesUpto).boxed().collect(new PrimesCollector());

	}

	public static boolean isGivenNumberPrime(int number) {
		int sqrtofNo = (int) Math.sqrt(number);
		return IntStream.range(2, sqrtofNo).noneMatch(each -> number % each == 0);
	}

	public static <T> List<T> takeAWhile(List<T> list, Predicate<T> predicate) {
		int i = 0;
		for (T t : list) {
			if (!predicate.test(t))
				return list.subList(0, i);
			else
				i++;
		}
		return list;
	}

	public static boolean isPrime(List<Integer> primes, int candidate) {

		int sqRoot = (int) Math.sqrt(candidate);

		List<Integer> listofPrimesUptoSqroot = takeAWhile(primes, i -> i <= sqRoot);

		return listofPrimesUptoSqroot.stream().noneMatch(eachPrime -> candidate % eachPrime == 0);

	}

	public static Map<Boolean, List<Integer>> partitionWithCustomCollector(int limit) {

		return IntStream.rangeClosed(0, limit).boxed().collect(

				() ->
		{
					HashMap<Boolean, List<Integer>> hashMap = new HashMap<Boolean, List<Integer>>();
					hashMap.put(true, new ArrayList<Integer>());
					hashMap.put(false, new ArrayList<Integer>());
					return hashMap;
				},

				(Map<Boolean, List<Integer>> accumulator, Integer x) ->
		{
					accumulator.get(isPrime(accumulator.get(true), x)).add(x);
				},

				(map1, map2) ->
		{
					map1.get(true).addAll(map2.get(true));
					map2.get(true).addAll(map2.get(true));
				});

	}

	public static void reductionAndSummarization() {

		long count = menu.stream().collect(counting());

		int maxCal = menu.stream().map(Dish::getCalories).reduce(Integer.MAX_VALUE, (a, b) -> a >= b ? a : b);

		int maxCal1stWay = menu.stream().collect(maxBy(comparing(Dish::getCalories))).get().getCalories();

		// Summarization

		int totalCalories = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);

		int totalCalories1stWay = menu.stream().mapToInt(Dish::getCalories).sum();

		ToIntFunction<Dish> toIntFunc = d -> d.getCalories();

		int totalCalories2ndWay = menu.stream().collect(summingInt(Dish::getCalories));

		double avgCalories = menu.stream().collect(averagingDouble(Dish::getCalories));

		IntSummaryStatistics summaryStats = menu.stream().collect(summarizingInt(Dish::getCalories));

		String shortMenu = menu.stream().map(Dish::getName).reduce("", (a, b) -> a + " " + b);

		String shortMenu2 = menu.stream().map(Dish::getName).collect(joining());

		int total_Calories = menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));

		Optional<Dish> maxDish = menu.stream().collect(reducing((dish1, dish2) -> dish1.getCalories() >= dish2.getCalories() ? dish1 : dish2));

		if (maxDish.isPresent()) {
			System.out.println(maxDish.get());
		}

		Stream<Integer> stream = Stream.of(1, 2, 3, 5, 6);

		List<Integer> collectStreamList = stream.collect(toList());

		collectStreamList = stream.reduce(new ArrayList<Integer>(), (List<Integer> l, Integer e) -> {
			l.add(e);
			return l;
		}, (List<Integer> l1, List<Integer> l2) -> {
			l1.addAll(l2);
			return l1;
		});

	}

}
