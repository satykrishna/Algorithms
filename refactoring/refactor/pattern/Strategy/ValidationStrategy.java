package refactor.pattern.Strategy;

@FunctionalInterface
public interface ValidationStrategy {

	public abstract boolean validate(String str);
}
