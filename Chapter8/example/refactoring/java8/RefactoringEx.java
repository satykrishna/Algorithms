package example.refactoring.java8;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.*;
import static example.refactoring.java8.AppLogger.*;

import static java.util.Comparator.*;

import static org.apache.log4j.Level.*;
import org.apache.log4j.Logger;

import example.data.collecting.CollectorsEx.CaloricLevel;
import example.input.streams.InputData;
import example.model.streams.Dish;

public class RefactoringEx {

	private static Logger logger = Logger.getLogger(RefactoringEx.class);

	public static void doSomeThing(Runnable runnable) {
		runnable.run();
	}

	public static void doSomeThing(Task task) {
		task.execute();
	}

	private static void redesignUsingStreamsandLambdas() {

		List<Dish> menu = InputData.getMenu();

		Function<Dish, CaloricLevel> classificationFunction =

				d -> {
					if (d.getCalories() >= 600)
						return CaloricLevel.FAT;
					else if (d.getCalories() > 500)
						return CaloricLevel.NORMAL;
					else
						return CaloricLevel.DIET;
				};

		Map<CaloricLevel, List<Dish>> dishesGroupByCaloricLevel =
				menu.stream().collect(groupingBy(classificationFunction));

		//CONCISE WAY
//		dishesGroupByCaloricLevel = menu.stream().collect(groupingBy(Dish::getCaloricLevel, toList()));

		menu.sort(comparing(Dish::getCalories));
		
		int totalCalories = menu.stream().mapToInt(Dish::getCalories).sum();
		

		log(logger, ERROR, ()-> "Total Number of Calories " + totalCalories);
		
		log(logger, INFO, ()-> ""+dishesGroupByCaloricLevel);
	}
	

	public static void main(String[] args) {
		doSomeThing((Runnable) () -> logger.info("Runnable"));
		redesignUsingStreamsandLambdas();
	}

}

interface Task {

	public abstract void execute();

}