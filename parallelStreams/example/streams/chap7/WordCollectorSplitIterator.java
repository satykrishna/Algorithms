package example.streams.chap7;

import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCollectorSplitIterator implements Spliterator<Character> {

	private String sentence;
	
	private int currentCharacter;
	
	public WordCollectorSplitIterator(String sentence) {
		this.sentence = sentence;
	}

	@Override
	public boolean tryAdvance(Consumer<? super Character> action) {
		//accept the current character and consume it
		action.accept(sentence.charAt(currentCharacter++));
		return currentCharacter < sentence.length();
	}

	@Override
	public Spliterator<Character> trySplit() {
		
		Spliterator<Character> newSplitItr = null;
		
		int currentSize = sentence.length() - currentCharacter;
		
		if(currentSize < 10) {
			return null;
		}
		
		for(int splitPos = currentSize/2+currentCharacter;
				splitPos < sentence.length();
				splitPos++) {
			
			if(Character.isWhitespace(sentence.charAt(currentCharacter))) {
				newSplitItr = 
						new WordCollectorSplitIterator(sentence.substring(currentCharacter, 
								splitPos));
				currentCharacter = splitPos;
				return newSplitItr;
			}
		}
		
		return newSplitItr;
	}

	@Override
	public long estimateSize() {
		return sentence.length()-currentCharacter;
	}

	@Override
	public int characteristics() {
		return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
	}

}
