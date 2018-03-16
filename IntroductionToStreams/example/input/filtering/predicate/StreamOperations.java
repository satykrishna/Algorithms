package example.input.filtering.predicate;

import java.util.List;
import static java.util.stream.Collectors.*;

import example.input.streams.InputData;
import example.model.streams.Dish;

public class StreamOperations {

	public static void filteringWithPredicate() {

		List<Dish> menu = InputData.getMenu();

		List<Dish> veggies = menu.stream().filter(Dish::isVegetarian).collect(toList());

		List<Dish> distinctVeggies = veggies.stream().distinct().collect(toList());

		// The first two meat dishes

		List<Dish> meatDishes = veggies.stream().
				filter(d -> !d.isVegetarian()).distinct().
				limit(2).
				collect(toList());
		
		List<String> dishNames = menu.stream().map(Dish::getName)
									.collect(toList());
		
		List<Integer> dishLengths = menu.stream().map(Dish::getName)
				.map(String::length).collect(toList());

	}

}
