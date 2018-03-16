package refactor.pattern.Strategy;

public class IsNumeric implements ValidationStrategy {

	@Override
	public boolean validate(String str) {
		return str.matches("\\d_+");
	}

}
