package example.java8.method;

public interface A {
	
	public default void hello() {
		System.out.println("Hello from A");
	}

}
