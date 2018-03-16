package example.streams.parallel;

import static java.lang.System.out;

import java.util.Spliterator;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import example.data.collecting.WordCounterSplitIterator;

public class IntroParallelStreams {

	public static void main(String[] args) {
		
		countWordsUsingStream("Satyakrishna Kondapalli");
		
		countWordsUsingParallelStream("Sai Kiran Kondapalli");

		
		
		
		/*out.println("Sequential sum is done in "
				+ measureSumPerformance(y -> IntroParallelStreams.sequentialSum(y), 10_000_000) + " milli seconds");
		out.println("Parallel sum is done in "
				+ measureSumPerformance(y -> IntroParallelStreams.parallelSum(y), 10_000_000) + " milli seconds");
		out.println("Iterative sum is done in " + measureSumPerformance(IntroParallelStreams::iterativeSum, 10_000_000)
				+ " milli seconds");
		out.println("Long range sum is done in " + measureSumPerformance(IntroParallelStreams::rangeSum, 10_000_0000)
				+ " milli seconds");
		out.println("Long range sum in parallel is done in "
				+ measureSumPerformance(IntroParallelStreams::rangeSuminParallel, 10_000_000) + " ms");
		out.println("Sum using Fork and Join is done in "
				+ measureSumPerformance(IntroParallelStreams::forkJoinSum, 10_000_000) + " msec");

*/		
	}
	
	public static int countWordsUsingParallelStream(String str) {
		Spliterator<Character> splitIterator = new WordCounterSplitIterator(str);
		Stream<Character> stream = StreamSupport.stream(splitIterator, true);
		WordCounter counter= stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
		out.println("No of words in " + str + "= "+counter.getCounter());
		return counter.getCounter();

	}

	public static int countWordsUsingStream(String str) {
		WordCounter counter= IntStream.range(0, str.length()).mapToObj(str::charAt)
				.reduce(new WordCounter(0, true), WordCounter::accumulate,
						WordCounter::combine);
		out.println("No of words in " + str + "= "+counter.getCounter());
		return counter.getCounter();
	}

	public static int countWords(String str) {

		Function<String, Integer> func = (s) -> {
			int counter = 0;
			boolean lastSpace = true;
			for (char c : s.toCharArray()) {
				if (Character.isWhitespace(c)) {
					lastSpace = true;
				} else {
					if (lastSpace) {
						counter++;
					}
					lastSpace = false;
				}
			}
			return counter;
		};

		return func.apply(str);
	}

	public static int countWordsIteratively(String s) {
		int counter = 0;
		boolean lastSpace = true;
		for (char c : s.toCharArray()) {
			if (Character.isWhitespace(c)) {
				lastSpace = true;
			} else {
				if (lastSpace) {
					counter++;
				}
				lastSpace = false;
			}
		}
		return counter;
	}

	public static long forkJoinSum(long n) {
		long[] numbers = LongStream.rangeClosed(1, n).toArray();
		ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
		return new ForkJoinPool().invoke(task);
	}

	public static long rangeSuminParallel(long n) {
		return LongStream.range(1, n).parallel().reduce(0, Long::sum);
	}

	public static long rangeSum(long n) {
		return LongStream.rangeClosed(1, n).reduce(0L, Long::sum);
	}

	public static long measureSumPerformance(Function<Long, Long> adder, long n) {
		long fastest = Long.MAX_VALUE;

		for (int i = 0; i < 10; ++i) {
			long start = System.nanoTime();
			long sum = adder.apply(n);
			long duration = (System.nanoTime() - start) / 1_000_000;
			// System.out.println("Result : " + sum);
			if (duration < fastest) {
				fastest = duration;
			}
		}
		return fastest;
	}

	public static long sequentialSum(long n) {
		return Stream.iterate(1L, i -> i + 1).limit(n).reduce(0L, Long::sum);
	}

	public static long iterativeSum(Long y) {
		long result = 0;

		for (long i = 1L; i <= y; ++i) {
			result += i;
		}

		return result;
	}

	public static long parallelSum(Long y) {
		return Stream.iterate(1L, num -> num + 1).limit(y).parallel().reduce(0L, (a, b) -> a + b);
	}

	static class Accumulator {
		public long total = 0;

		public void add(long value) {
			total += value;
		}
	}

	public static long sideEffectsum(long n) {
		Accumulator acc = new Accumulator();
		LongStream.rangeClosed(1, n).forEach(acc::add);
		return acc.total;
	}

	public static long sideEffectParallelSum(long n) {
		Accumulator acc = new Accumulator();
		LongStream.rangeClosed(1, n).parallel().forEach(acc::add);
		return acc.total;
	}

}
