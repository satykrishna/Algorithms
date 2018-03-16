package refactor.pattern.chain;

public class HeaderTextProcessing extends ProcessingObject<String> {

	@Override
	protected String handleWork(String input) {
		return "From Raul, Mario and Arun : " +input;
	}

}
