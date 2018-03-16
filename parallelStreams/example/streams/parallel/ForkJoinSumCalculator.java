package example.streams.parallel;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

	private static final long serialVersionUID = 1L;
	
	private final long[] numbers;
	
	private final int start;
	
	private final int end;
	
	public static final long THRESHOLD = 10_000;

	public ForkJoinSumCalculator(long[] numbers) {
		this(numbers, 0, numbers.length);
	}
	
	private ForkJoinSumCalculator(long[] numbers, int start, int end) {
		this.numbers = numbers;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		int length = end - start;
		
		int mid = (start)+length/2;
		
		if(length <=THRESHOLD) {
			return computeSequentially();
		}
		
		ForkJoinSumCalculator leftTask 
			= new ForkJoinSumCalculator(numbers, start, mid);
		
		leftTask.fork();
		
		ForkJoinSumCalculator rightTask 
		   = new ForkJoinSumCalculator(numbers, mid, end);
		
		Long rightResult = rightTask.compute();
		
		Long leftResult = leftTask.join();
		
		return leftResult + rightResult;
	}

	private Long computeSequentially() {
		long sum = 0;
		
		for(int i = start; i <end; ++i) {
			sum +=numbers[i];
		}
		
		return sum;
	}

}
