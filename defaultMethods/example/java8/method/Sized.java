package example.java8.method;

public interface Sized {
	
	public abstract int sized();
	
	default boolean isEmpty() {
		return sized() == 0;
	}

}
