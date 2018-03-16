package chap6.data.collecting;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;

import static java.util.Comparator.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import example.input.streams.InputData;
import example.model.streams.Dish;

public class Chap6Ex {
	
	
	private static List<Dish> menu = InputData.getMenu();
	
	public static void reductionAndSummarization() {
	
		long count = menu.stream().collect(counting());
		
		int maxCal = menu.stream().map(Dish::getCalories).reduce(Integer.MAX_VALUE, (a, b)-> a >= b? a:b);
		
		int maxCal1stWay = menu.stream().collect(maxBy(comparing(Dish::getCalories))).get().getCalories();
		
		//Summarization
		
		int totalCalories = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
		
		int totalCalories1stWay = menu.stream().mapToInt(Dish::getCalories).sum();
		
		ToIntFunction<Dish> toIntFunc = d ->d.getCalories();
		
		int totalCalories2ndWay = menu.stream().collect(summingInt(Dish::getCalories));
		
		double avgCalories = menu.stream().collect(averagingDouble(Dish::getCalories));
		
		IntSummaryStatistics summaryStats = menu.stream().collect(summarizingInt(Dish::getCalories));
		
		String shortMenu = menu.stream().map(Dish::getName).reduce("", (a, b)-> a + " "+b );
		
		String shortMenu2 = menu.stream().map(Dish::getName).collect(joining());
		
		int total_Calories = menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
		
		Optional<Dish> maxDish = menu.stream().collect(reducing((dish1, dish2)-> dish1.getCalories() >=dish2.getCalories()? dish1:dish2));
		
		if(maxDish.isPresent()) {
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
