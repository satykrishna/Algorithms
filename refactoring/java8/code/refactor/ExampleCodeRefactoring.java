package java8.code.refactor;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import example.data.collecting.CollectorsEx.CaloricLevel;
import example.input.streams.InputData;
import example.model.streams.Dish;

public class ExampleCodeRefactoring {

	private static Logger logger = Logger.getLogger(ExampleCodeRefactoring.class.getName());
	
	public static void main(String[] args) {

		List<Dish> menu = InputData.getMenu();

		menu.stream().collect(groupingBy(dish -> {
			if (dish.getCalories() <= 400) {
				return CaloricLevel.DIET;
			} else if (dish.getCalories() <= 700) {
				return CaloricLevel.NORMAL;
			} else {
				return CaloricLevel.FAT;
			}
		}));

		menu.stream().collect(groupingBy(Dish::getCaloricLevel));
		menu.sort(comparing(Dish::getCalories));

		int totalCalories = menu.stream().map(Dish::getCalories).reduce(0, (a, b) -> a + b);

		totalCalories = menu.stream().collect(summingInt(Dish::getCalories));

		 List<String> dishNames = new ArrayList<String>();

		for (Dish each : menu) {
			if (each.getCalories() > 300) {
				dishNames.add(each.getName());
			}
		}

		final List<String> dishNamesGreaterthan300 = menu.stream().filter(d -> d.getCalories() > 300).map(Dish::getName).collect(toList());
		
		logger.log(Level.WARNING, ()->"menu dishes : " + dishNamesGreaterthan300);
	}
}
