package example.async.java8;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.*;

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
		return r.nextDouble() * product.charAt(0) + 2 * product.charAt(1);
	}

	public static void delay() {
		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public Future<Double> getPriceAsync(String product) {
		CompletableFuture<Double> futurePrice = new CompletableFuture<Double>();
		new Thread(() -> futurePrice.complete(calculatePrice(product))).start();
		return futurePrice;
	}

	public Future<Double> getPriceAysncUsingCompleteableFutureStaticMethods(String product) {
		return supplyAsync(()->calculatePrice(product));
	}
	
	
	public String getPriceUpdated(String product) {
		double price = calculatePrice(product);
		Random random = new Random();
		Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
		return String.format("%s:%.2f:%s", name, price, code);
	}
}
