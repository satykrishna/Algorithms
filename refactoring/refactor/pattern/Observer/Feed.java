package refactor.pattern.Observer;

import java.util.ArrayList;
import java.util.List;

public class Feed implements Subject {
	
	private List<Observer> observers = 
			new ArrayList<Observer>();

	@Override
	public void registerObserver(Observer observer) {
		this.observers.add(observer);
	}

	@Override
	public void notifyObservers(String tweet) {
		observers.forEach(observer->observer.notify(tweet));
	}

}
