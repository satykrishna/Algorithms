package example.async.java8;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

public class FutureEx {

	private static List<Shop> shops;

	static {
		shops = Arrays.asList(new Shop("Best Price"), new Shop("Lets save Big"), new Shop("My Fav Shop"),
				new Shop("Buy it all"));

	}

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();

		Future<Double> future = executor.submit(new Callable<Double>() {

			@Override
			public Double call() throws Exception {
				return doSomeLongComputation();
			}

			private Double doSomeLongComputation() {
				// TODO Auto-generated method stub
				return null;
			}

		});

		doSomeThingElse();

		try {
			Double result = future.get(1, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}

		Shop shop = new Shop("Best Shop");

		long start = System.nanoTime();

		Future<Double> futurePrice = shop.getPriceAsync("my favorite product");

		long end = System.nanoTime();

		long invocationTime = (end - start) / 1_000_000;

		System.out.println("Inovcation returned after " + invocationTime + " msec");

		doSomeThingElse();

		try {
			double price = futurePrice.get();
			System.out.printf("Price is %.2f%n", price);
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}

		end = System.nanoTime();

		long retrievalTime = (end - start) / 1_000_000;

		System.out.println("Price returned after : " + retrievalTime + " msec");
		
		start = System.currentTimeMillis();
		
//		System.out.println(findPrices("MyPhone27s"));
		System.out.println(findPricesParallel("MyPhone27s"));
		
		end = System.currentTimeMillis();
		
		long duration = (end - start);
		
		System.out.println("Done in " + duration + " milli seconds");

	}

	public static List<String> findPrices(String product) {
		return shops.stream().map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
				.collect(Collectors.toList());
	}
	
	public static List<String> findPricesParallel(String product) {
		return shops.parallelStream().map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
				.collect(Collectors.toList());
	}

	private static void doSomeThingElse() {
		// TODO Auto-generated method stub

	}
}
