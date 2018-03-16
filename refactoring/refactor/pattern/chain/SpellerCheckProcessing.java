package refactor.pattern.chain;

public class SpellerCheckProcessing extends ProcessingObject<String> {

	@Override
	protected String handleWork(String input) {
		return input.replaceAll("labda", "lambda");
	}

}
