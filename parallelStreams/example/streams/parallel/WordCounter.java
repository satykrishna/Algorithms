	package example.streams.parallel;

public class WordCounter {

	private int counter;
	
	private boolean lastSpace;
	
	public WordCounter(int counter, boolean lastSpace) {
		this.counter = counter;
		this.lastSpace = lastSpace;
	}
	
	public WordCounter accumulate(Character c) {
		
		if(Character.isWhitespace(c)) {
			return lastSpace?this: new WordCounter(	counter, true);
		}
		else {
			return lastSpace?new WordCounter(counter+1, false):this;
		}
	}
	
	public WordCounter combine(WordCounter wordCounter) {
		return new WordCounter(counter+wordCounter.counter, wordCounter.lastSpace);
	}
	
	public int getCounter() {
		return counter;
	}

	public boolean isLastSpace() {
		return lastSpace;
	}

	public void setLastSpace(boolean lastSpace) {
		this.lastSpace = lastSpace;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	@Override
	public String toString() {
		return "WordCounter [counter=" + counter + ", lastSpace=" + lastSpace + "]";
	}
}
