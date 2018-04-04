package example.streams.chap7;


public class WordCollector {

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

	public void accumulate(Character ch) {
		if (Character.isWhitespace(ch)) {
			if (lastSpace) {
				return;
			}
			else {
				lastSpace = true;
			}
			// return lastSpace ? this : new WordCollector(true, count);
		}
		else {
			// return lastSpace ? new WordCollector(false, count + 1) : this;
			count++;
		}
	}

	public void combine(WordCollector wc2) {
		// return new WordCollector(true, this.count + wc2.count);
		this.count += wc2.count;
	}

	
	public WordCollector accumulator(Character ch) {
		
		if(Character.isWhitespace(ch)) {
			return lastSpace? this:new WordCollector(true, count);
		}
		else {
			return lastSpace? new WordCollector(false, count+1):this;
		}
	}
	
	public WordCollector combineForParallelOperation(WordCollector wordCollector) {
		return new WordCollector(true, this.count + wordCollector.count);
	}
}

