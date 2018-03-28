package example.streams.chap7;

import java.util.Spliterator;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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

	public static int countWordsUsingStreamsWithExternalCollectorWithoutParallel(String sentence) {
		return IntStream.range(0, sentence.length()).mapToObj(sentence::charAt).collect(new WhiteSpaceCollector());
	}

	public static int countWordsUsingInternalCollectorusingCollectOperation(String sentence) {
		return IntStream.range(0, sentence.length())
		.mapToObj(sentence::charAt)
		.collect(()-> new WordCollector(true, 0), 
				(WordCollector wc, Character ch)-> {
					if(Character.isWhitespace(ch)) {
						if(!wc.isLastSpace()) {
							wc.setLastSpace(true);
							wc.setCount(wc.getCount()+1);
						}
					}
					else {
						wc.setLastSpace(false);
					}
					
				}, 
				(wc1, wc2)-> {
					wc1.setCount(wc2.getCount()+1);
					wc1.setLastSpace(true);
				}).getCount();
 	}

	public static int countWordsUsingCollectButUsingWordCollectorMethods(String sentence) {
		
		WordCollector wordCollector = IntStream.range(0, sentence.length())
				.mapToObj(sentence::charAt)
				.collect(()-> new WordCollector(false, 0), WordCollector::accumulate, WordCollector::combine);
		
		return wordCollector.getCount();
		
	}
	
	public static int countWordsUsingReduceOps(String sentence ) {
		WordCollector wc = IntStream.range(0, sentence.length())
				.mapToObj(sentence::charAt)
				.reduce(new WordCollector(true, 0), WordCollector::accumulator, WordCollector::combineForParallelOperation);
		
		return wc.getCount();
	}
	
	
	public static int countWordsUsingSplitIteratorParallelStream(String sentence) {
		 Spliterator<Character> splitIterator = new WordCollectorSplitIterator(sentence);
		 Stream<Character> stream = StreamSupport.stream(splitIterator, true);
		 return stream.reduce(new WordCollector(true, 0), WordCollector::accumulator, WordCollector::combineForParallelOperation).getCount();
	}
	
	
	public static void main(String[] args) {
		// comparePerformance(1000000);
//		logger.info(countWordsUsingStreamsWithExternalCollectorWithoutParallel("S A T Y A"));
		logger.info(countWordsUsingCollectButUsingWordCollectorMethods("S A T Y A"));

	}
	
}