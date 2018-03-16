package refactor.pattern.Strategy;

public class IsAllLowerCase implements ValidationStrategy {

	@Override
	public boolean validate(String str) {
		return str.matches("[a-z]+");
	}

}
