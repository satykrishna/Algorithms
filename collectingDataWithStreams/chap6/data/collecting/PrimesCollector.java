package chap6.data.collecting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.*;

public class PrimesCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

	@Override
	public Supplier<Map<Boolean, List<Integer>>> supplier() {
		return () -> {
			HashMap<Boolean, List<Integer>> map = new HashMap<Boolean, List<Integer>>();
			map.put(true, new ArrayList<Integer>());
			map.put(false, new ArrayList<Integer>());
			return map;
		};
	}

	@Override
	public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
		return (Map<Boolean, List<Integer>> map, Integer newElement) -> {
			map.get(isPrime(map.get(true), newElement)).add(newElement);
		};
	}

	@Override
	public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
		return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
			map1.get(true).addAll(map2.get(true));
			map1.get(true).addAll(map2.get(true));
			return map1;
		};
	}

	@Override
	public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
		return Function.identity();
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
	}

	public <T> List<T> takeAWhile(List<T> list, Predicate<T> predicate) {
		int i = 0;
		for (T t : list) {
			if (!predicate.test(t))
				return list.subList(0, i);
			else
				i++;
		}
		return list;
	}

	public boolean isPrime(List<Integer> primes, int candidate) {
		int sqRoot = (int) Math.sqrt(candidate);
		List<Integer> listofPrimesUptoSqroot = takeAWhile(primes, i -> i <= sqRoot);
		return listofPrimesUptoSqroot.stream().noneMatch(eachPrime -> candidate % eachPrime == 0);
	}
}
