package refactor.pattern.Observer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LeMonde implements Observer{

	private static final Logger LOGGER = 
			Logger.getLogger(LeMonde.class.getName());
	@Override
	public void notify(String tweet) {
		if(tweet != null && tweet.contains("wine")) {
			LOGGER.log(Level.INFO, ()->"Today Cheese wine and news !!! "+tweet);
		}
	}
	

}
