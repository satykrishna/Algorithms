package example.java8.fp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class FunctionalProgrammingEx {

	public static List<List<Integer>> subsets(List<Integer> list) {
		if (list.isEmpty()) {
			List<List<Integer>> newList = new ArrayList<>();
			newList.add(Collections.emptyList());
			return newList;
		}
		Integer first = list.get(0);
		List<Integer> rest = list.subList(1, list.size());
		List<List<Integer>> subAns = subsets(rest);
		List<List<Integer>> subAns2 = insertAll(first, subAns);
		return concat(subAns, subAns2);
	}

	private static List<List<Integer>> concat(List<List<Integer>> a, List<List<Integer>> b) {
		List<List<Integer>> result = new ArrayList<>();
		result.addAll(a);
		result.addAll(b);
		return result;
	}

	private static List<List<Integer>> insertAll(Integer first, List<List<Integer>> lists) {

		List<List<Integer>> result = new ArrayList<>();

		for (List<Integer> l : lists) {
			List<Integer> copyList = Collections.emptyList();
			copyList.add(first);
			copyList.addAll(l);
			result.add(copyList);
		}

		return result;
	}
	
	public static long streamFactorial(int upperLimit) {
		return LongStream.rangeClosed(1, upperLimit)
					.reduce(1, (a, b)-> a*b);
	}

	public static long iterativeFactorial(int upperLimit) {
		
		int result  = 1;
		
		for (int i = 0; i<=upperLimit; i++) {
			result*=i;
		}
		
		return result;
	}
	
	public static long recursiveFactorial(int upperLimit) {
		if(upperLimit  == 1) {
			return 1;
		}
		else {
			return upperLimit * recursiveFactorial(upperLimit-1);
		}
	}
	
	public static long recursiveFactorialOtherWay(int upperLimit) {
		return recursiveFactorialOtherWay(1, upperLimit);
	}

	private static long recursiveFactorialOtherWay(int acc, int upperLimit) {
		return acc == 1? 1 :recursiveFactorialOtherWay(acc*upperLimit, upperLimit-1);
	}

	private static boolean isPrime(int givenNumber) {
		
		int sqrtOfGivenNo = (int) Math.sqrt(givenNumber);
		
		return IntStream.rangeClosed(2, sqrtOfGivenNo)
				.noneMatch(i->givenNumber%i==0);
	}
	
	private static Stream<Integer> listofPrimes(int number) {
		return Stream.iterate(2, i->i+1)
				.filter(FunctionalProgrammingEx::isPrime)
				.limit(number);
	}
	
	private static IntStream numbers( int num) {
		return IntStream.range(2, num);
	}
	
	private static int head(IntStream numbers) {
		return numbers.findFirst().getAsInt();
	}
	
	private static IntStream getTail(IntStream numbers) {
		return numbers.skip(1);
	}
	
	private static IntStream findPrimes(int num) {
		IntStream intStream = numbers(num);
		int head = head(intStream);
		IntStream filtered = getTail(intStream).filter(n->n%head!=0);
		return IntStream.concat(IntStream.of(head), filtered);
	}
	
	
} 
