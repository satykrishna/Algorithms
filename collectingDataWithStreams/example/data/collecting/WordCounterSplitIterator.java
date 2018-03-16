package example.data.collecting;

import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCounterSplitIterator implements Spliterator<Character> {

	private String string;
	
	private int currentChar;
	
	public WordCounterSplitIterator(String string) {
		this.string = string;
	}
	
	@Override
	public boolean tryAdvance(Consumer<? super Character> action) {
		action.accept(string.charAt(currentChar++));
		return currentChar < string.length();
	}

	@Override
	public Spliterator<Character> trySplit() {
		int currentSize = string.length()-currentChar;
		if(currentSize < 10) 
			return null;
		for(int splitPos = currentSize/2+currentChar;
				splitPos<string.length(); splitPos++) {
			if(Character.isWhitespace(string.charAt(splitPos))) {
				Spliterator<Character> splititerator =
						new WordCounterSplitIterator(string.substring(currentChar, splitPos));
				currentChar = splitPos;
				return splititerator;
			}
		}
		
		return null;
	}

	@Override
	public long estimateSize() {
		return string.length() - currentChar;
	}

	@Override
	public int characteristics() {
		return ORDERED+SIZED+SUBSIZED+NONNULL+IMMUTABLE;
	}

}
