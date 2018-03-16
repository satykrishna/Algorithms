package refactor.pattern.Observer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NYTimes implements Observer {

	private static final Logger LOGGER = Logger.getLogger(NYTimes.class.getName());
	
	@Override
	public void notify(String tweet) {
		if(tweet != null && tweet.contains("money")) {
			LOGGER.log(Level.INFO, ()->"Breaking News in NY!!! "+tweet );
		}
	}


}
