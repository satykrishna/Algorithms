package java8.lambda.examples;

@FunctionalInterface
public interface TriFunction<T, U, V, X> {

	public abstract X create(T t, U u, V v);
}
