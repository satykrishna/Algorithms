package refactor.pattern.chain;

public abstract class ProcessingObject<T> {

	protected ProcessingObject<T> successor;

	public void setSuccessor(ProcessingObject<T> successor) {
		this.successor = successor;
	}

	public T handle(T input) {
		T result = handleWork(input);
		return successor == null ? result : successor.handle(result);
	}

	protected abstract T handleWork(T input);
}
