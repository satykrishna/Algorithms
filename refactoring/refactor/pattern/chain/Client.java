package refactor.pattern.chain;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Client {

	public static void main(String[] args) {
		ProcessingObject<String> p1 = new HeaderTextProcessing();
		ProcessingObject<String> p2 = new SpellerCheckProcessing();
		
		p1.setSuccessor(p2);
		
		String result = p1.handle("Are labdas really good?");
		
		System.out.println(result);
		
		UnaryOperator<String> headerProcessor =
				(text)->"From Raul, Mario and Alan : "+text;
				
		UnaryOperator<String> spellChecker = 
				text->text.replaceAll("labda","lambda");
				
		Function<String, String> pipeline = 
				headerProcessor.andThen(spellChecker);
		
		result = pipeline.apply("Are labdas really good?");
		
		System.out.println(result);
	}
}
