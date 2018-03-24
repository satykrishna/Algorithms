package example.streams.chap7;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class FindSumTask extends RecursiveTask<Long> {

	private static final long serialVersionUID = 1L;

	private long[] numbers;
	private int start;
	private int end;

	private static final int THRESHOLD = 10_000;

	public FindSumTask(long[] numbers) {
		this(numbers, 0, numbers.length);
	}

	private  FindSumTask(long[] numbers, int start, int end) {
		this.numbers = numbers;
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected Long compute() {
		int length = end - start;
		
		if(length <= THRESHOLD)
			return computeSequentially();
		
		FindSumTask leftTask = new FindSumTask(numbers, start, start+length/2);
		leftTask.fork();
		FindSumTask rightTask = new FindSumTask(numbers, start+length/2, end);
		Long resultFromRightTaskComputation =  rightTask.compute();
		Long resultFromLeftTaskComputation = leftTask.join();
		return (resultFromLeftTaskComputation + resultFromRightTaskComputation);
	}

	private Long computeSequentially() {
		return Arrays.stream(numbers, start, end).sum();
	}

}
