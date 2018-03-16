package java8.lambda.examples;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambda {
	
	
	public static <T, R> void map(List<T> list, Function<T, R> function) {
		List<R> newList = new ArrayList<R>();
		for(T t : list) {
			newList.add(function.apply(t));
		}
		System.out.println(newList);
		System.out.println("_________");
	}

	public static <T> void filter(List<T> list, Predicate<T> predicate) {
		List<T> result = new ArrayList<T>();
		for (T each : list) {
			if (predicate.test(each)) {
				result.add(each);
			}
		}
		System.out.println(result);
		System.out.println("---------");
	}

	public static void readFile(BufferReadProcessor processor, String file) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String result = processor.processGivenFile(reader);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		final String file = "/home/satya/Desktop/advJava/algos/algorithms/java8/java8/lambda/examples/Lambda.java";
		readFile((BufferedReader reader) -> reader.readLine() + reader.readLine(), file);
		List<Integer> intList = Arrays.asList(1, 2, 3, 4, 101, 5);
		filter(intList, (Integer no) -> (no % 2 == 0));
		filter(intList, (Integer number) -> (number % 4 == 1));
		List<String> stringList = Arrays.asList("Satya", "Krishna", "Kondapalli", "Sai", "Kiran");
		filter(stringList, (String str) -> {
			return str.length() == 10;
		});
		forEach(intList, (Integer each) -> {
			int y = each * 2;
			System.out.println(each + "*2=" + y);
		}, null);
		forEach(intList, (Integer each) -> {
			int y = each * 2;
			System.out.println(each + "*2=" + y);
		}, (Integer each) -> each % 2 == 1);
		
		map(intList, (Integer x)->"x"+x);
		
		IntPredicate evenPredicate = (int number)->(number%2==0);
		IntPredicate oddPredicate = (evenPredicate.negate());
		System.out.println(evenPredicate.test(1000));
		System.out.println(oddPredicate.test(101));
//		BinaryOperator<String> t = null;
		Predicate<List<String> > predicate = (List<String> l)->!l.isEmpty();
		System.out.println(predicate.test(stringList));
	
		BufferedReader reader1 = new BufferedReader(new FileReader(file));
		
		Function<BufferedReader, String> function = (BufferedReader reader)->{
			System.out.println("---------");
			try {
				return reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return null;
		};
		
		String line = function.apply(reader1);
		System.out.println(line);
	}

	public static <T> void forEach(List<T> list, Consumer<T> consumer, Predicate<T> predicate) {
		if (predicate != null) {
			for (T t : list) {
				if (predicate.test(t)) {
					consumer.accept(t);
				}
			}
		} else {
			for (T t : list) {
				consumer.accept(t);
			}
		}
		System.out.println("---------");
	}

}
