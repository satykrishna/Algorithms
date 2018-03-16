package example.java8.method;

public interface B extends A {

	public default void hello() {
		System.out.println("Hello from B");
	}
}
