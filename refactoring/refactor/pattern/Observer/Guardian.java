package refactor.pattern.Observer;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Guardian implements Observer {
	
	private static final Logger LOGGER = Logger.getLogger(Logger.class);

	@Override
	public void notify(String tweet) {
		if(tweet != null && tweet.contains("queen")) {
			LOGGER.log(Level.INFO, "Yet another news in London " + tweet);
		}
	}

}
