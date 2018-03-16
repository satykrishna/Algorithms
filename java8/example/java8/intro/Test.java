package example.java8.intro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class Test {

	public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
		List<R> result = new ArrayList<R>();
		for (T t : list) {
			R r = f.apply(t);
			result.add(r);
		}
		return result;
	}

	public static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> results = new ArrayList<T>();

		for (T t : list) {
			if (p.test(t)) {
				results.add(t);
			}
		}
		return results;
	}

	public static String processFile(BufferedReaderProcessor p) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
			return p.process(reader);
		}
	}

	public static <T> void forEach(List<T> list, Consumer<T> c) {
		for (T t : list) {
			c.accept(t);
		}
	}

	public static void main(String[] args) throws IOException {

		String oneLine = processFile((BufferedReader reader) -> reader.readLine());
		String twoLines = processFile((BufferedReader reader) -> reader.readLine() + reader.readLine());
		List<String> arrayList = new ArrayList<String>();
		arrayList.add(oneLine);
		arrayList.add(twoLines);
		arrayList = filter(arrayList, (String s) -> s.length() > 3);
		forEach(arrayList, (String s) -> {
			System.out.println(s);
		});
		List<Integer> list = map(arrayList, (String s) -> s.length());

		IntPredicate evenNumbers = (int i) -> i % 2 == 0;

		System.out.println(evenNumbers.test(1));
		
		List<Apple> inventory = new ArrayList<Apple>();
		
		
		inventory.sort(java.util.Comparator.comparing(Apple::getWeight));
	}

}
