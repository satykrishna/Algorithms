package refactor.pattern.Observer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

	private static final Logger LOGGER = 
			Logger.getLogger(Client.class.getName());
	
	public static void main(String[] args) {
		
		Feed feed = new Feed();
		
		feed.registerObserver(new Guardian());
		feed.registerObserver(new LeMonde());
		feed.registerObserver(new NYTimes());
		
		
		//using lambdas
		feed.registerObserver((String tweet)->{
			if(tweet != null && tweet.contains("money"))
				LOGGER.log(Level.INFO, ()->"Yet another news in London...!!! " + tweet);
		});
		
		feed.registerObserver((String tweet)->{
			if(tweet != null && tweet.contains("wine"))
				LOGGER.log(Level.INFO, ()->"Today Cheese wine and news !!! "+tweet);
		});
		
	}
}
