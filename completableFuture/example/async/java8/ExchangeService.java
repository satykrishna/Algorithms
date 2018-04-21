package example.async.java8;

public class ExchangeService {
	
	public static void delay() {
		try {
			Thread.sleep(1000L);
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	

	public static double getRate(Money m1, Money m2) {
		delay();
		//get exchangeRate by querying;
		return Math.random();
	}
	
}
