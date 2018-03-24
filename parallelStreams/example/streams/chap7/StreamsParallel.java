package example.streams.chap7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

public class StreamsParallel {

	private static final Logger logger = Logger.getLogger(StreamsParallel.class);

	public StreamsParallel() {
	}

	public static long parallelSumUsingStream(long numberUpTo) {
		return Stream.iterate(0, i -> i + 1).limit(numberUpTo).parallel().reduce(0, Integer::sum);
	}

	public static long sequentialSumUsingStream(long numberUpTo) {
		return Stream.iterate(0, i -> i + 1).limit(numberUpTo).reduce(0, Integer::sum);
	}

	public static long sequentialSumUsingLongStream(long numberUpTo) {
		return LongStream.rangeClosed(0, numberUpTo).sum();
	}

	public static long parallelSumUsingLongStream(long numberUpTo) {
		return LongStream.rangeClosed(0, numberUpTo).parallel().sum();
	}

	public static long measureSumPerformance(Function<Long, Long> fnAdder, long n) {
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; ++i) {
			long startElapsedTime = System.nanoTime();
			long sum = fnAdder.apply(n);
			long duration = (System.nanoTime() - startElapsedTime) / 1000;
			logger.debug("Sum [" + sum + " ]in " + i + " iteration took(milli sec) " + duration);
			if (duration < fastest) {
				fastest = duration;
			}
		}
		return fastest;
	}

	public static void comparePerformance(final long n) {
		logger.info("[Stream]Sequential Sum is done in " + measureSumPerformance(StreamsParallel::sequentialSumUsingStream, n));
		logger.info("_----------------------------");
		logger.info("[Stream]Parallel Sum is done in " + measureSumPerformance(StreamsParallel::parallelSumUsingStream, n));
		logger.info("-----------------------------");
		logger.info("[LongStream]Sequential Sum is done in " + measureSumPerformance(StreamsParallel::sequentialSumUsingLongStream, n));
		logger.info("-----------------------------");
		logger.info("[LongStream]Parallel Sum is done in " + measureSumPerformance(StreamsParallel::parallelSumUsingLongStream, n));
		logger.info("-----------------------------");
		logger.info("[Side efect] accumulator sum is done in " + measureSumPerformance(StreamsParallel::sideEffectParallelSum, n));
		logger.info("------------------------------");
		logger.info("[ForkJoinThreadPool] sum is done in " + measureSumPerformance(StreamsParallel::sideEffectParallelSum, n));
	}

	public static long sideEffectParallelSum(long n) {
		Accumulator accumulator = new Accumulator();
		LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
		return accumulator.total;
	}

	public static long findSumUsingForJoin(long n) {
		FindSumTask task = new FindSumTask(LongStream.range(0, n).toArray());
		return new ForkJoinPool().invoke(task);
	}

	public static int countWordsIteratively(String sentence) {
		int count = 0;
		boolean lastSpace = true;
		for (char ch : sentence.toCharArray()) {
			if (Character.isWhitespace(ch)) {
				lastSpace = true;
			}
			else {
				lastSpace = false;
			}
			if (lastSpace) {
				count++;
			}
		}
		return count;
	}

	public static int countWordsUsingStreamsWithExternalCollector(String sentence) {
		return IntStream.range(0, sentence.length()).mapToObj(sentence::charAt).parallel().collect(new WhiteSpaceCollector());
	}

	public static int countWordsUsingInternalCollector(String sentence) {
		return 1;
	}

	class WordCollector {

		private boolean lastSpace;
		private int count;

		public boolean isLastSpace() {
			return lastSpace;
		}

		public void setLastSpace(boolean lastSpace) {
			this.lastSpace = lastSpace;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public WordCollector(boolean lastSpace, int count) {
			super();
			this.lastSpace = lastSpace;
			this.count = count;
		}

		public WordCollector accumulate(Character ch) {

			if (Character.isWhitespace(ch)) {
				return lastSpace ? this : new WordCollector(true, count);
			}
			else {
				return lastSpace ? new WordCollector(false, count + 1) : this;
			}
		}

		public WordCollector combine(WordCollector wc2) {
			return new WordCollector(true, this.count + wc2.count);
		}
	}

	public static void main(String[] args) {
		// comparePerformance(1000000);
		logger.info(countWordsUsingStreamsWithExternalCollector("S A T Y A"));

	}
}
