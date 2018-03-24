package example.streams.chap7;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;


public class WhiteSpaceCollector implements Collector<Character, Integer, Integer> {

	private AtomicBoolean  lastSpace = new AtomicBoolean(false);
	private AtomicLong accumulator = new AtomicLong(0);
	
	@Override
	public Supplier<Integer> supplier() {
		return ()-> 0;
	}

	// A B C D SPACE E F SPACE E E SPACE SPACE E E E 
	
	@Override
	public BiConsumer<Integer, Character> accumulator() {
		return (i, ch)-> {
			if(Character.isWhitespace(ch)) {
				if(!lastSpace.get()) {
					lastSpace.set(true);;
					accumulator.getAndIncrement();
				}
			}
			else {
				lastSpace.set(false);;
			}
		};
	}

	@Override
	public BinaryOperator<Integer> combiner() {
		return (i, j)-> i+j;
	}

	@Override
	public Function<Integer, Integer> finisher() {
		return i ->(int) accumulator.get();
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(Characteristics.CONCURRENT));
	}

}
