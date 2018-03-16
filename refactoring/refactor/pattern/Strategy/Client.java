package refactor.pattern.Strategy;

public class Client {

	public static void main(String[] args) {
		
		Validator numericValidator = new Validator(new IsNumeric());
		
		boolean b1 = numericValidator.validate("1234");
		boolean b2 = numericValidator.validate("abc");
		
		Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
		
		b1 = lowerCaseValidator.validate("ABC");
		b2 = lowerCaseValidator.validate("abc");
		
		Validator validationUsingLambda = 
				new Validator((String str)-> str.matches("\\d+"));
		validationUsingLambda.validate("aaa");
	}
}
