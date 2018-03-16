package example.async.java8;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {
	
	private String name;

	public String getName() {
		return name;
	}

	public Shop(String name) {
		this.name = name;
	}

	public double getPrice(String product) {
		return calculatePrice(product);
	}
	
	private double calculatePrice(String product) {
		delay();
		Random r = new Random();
		return r.nextDouble()*product.charAt(0)+2*product.charAt(1);
	}

	public static void delay() {
		try {
			Thread.sleep(1000L);
		}catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Future<Double> getPriceAsyncOld(String product) {
		
		CompletableFuture<Double> futurePrice = 
				new CompletableFuture<Double>();
		
		new Thread(()->{
			double price = calculatePrice(product);
			futurePrice.complete(price);
		}).start();
		
		return futurePrice;
		
	}
	
	
	public Future<Double> getPriceAsyncException(String product) {

		CompletableFuture<Double> futurePrice = 
				new CompletableFuture<Double>();
		
		new Thread(()->{
			try {
				double price = calculatePrice(product);
				futurePrice.complete(price);		
			}
			catch(Exception e) {
				futurePrice.completeExceptionally(e);
			}
		
		}).start();
		
		return futurePrice;
	}
	
	public Future<Double> getPriceAsync(String product) {
		return CompletableFuture.supplyAsync(()->calculatePrice(product)); 
	}
}
