package refactor.pattern.Strategy;

public class Validator {
	
	private final ValidationStrategy strategy;

	public Validator(ValidationStrategy strategy) {
		super();
		this.strategy = strategy;
	}
	
	public boolean validate(String str) {
		return strategy.validate(str);
	}
	

}
