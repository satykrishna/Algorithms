package example.async.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.*;
import static java.lang.System.*;

import org.apache.log4j.Logger;

import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

import java.util.ArrayList;

public class FutureEx {

	private static Logger logger = Logger.getLogger(FutureEx.class);

	public static void useAsyncApiForShop() {
		Shop shop = new Shop("Best Shop1");
		long start = System.currentTimeMillis();
		Future<Double> futurePrice = shop.getPriceAsync("My FAV PRODUCT");
		long invocationTime = System.currentTimeMillis() - start;
		doCheckThePriceFromOtherShops();
		try {
			double price = futurePrice.get();
			logger.info("Price is : " + price);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		long retrievalTime = System.currentTimeMillis() - start;
		logger.info("Price Returned after " + retrievalTime + " msec");
	}

	private static void doCheckThePriceFromOtherShops() {
		Shop shop = new Shop("Best Shop2");
		long start = System.currentTimeMillis();
		Future<Double> futurePrice = shop.getPriceAsync("My FAV PRODUCT");
		long invocationTime = System.currentTimeMillis() - start;
		try {
			double price = futurePrice.get();
			logger.info("Price is : " + price);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		long retrievalTime = System.currentTimeMillis() - start;
		logger.info("Price Returned after " + retrievalTime + " msec");
	}

	static List<Shop> shopList = new ArrayList<>();

	static {
		for (int i = 0; i < 400; i++) {
			shopList.add(new Shop("shop #" + i));
		}
	}

	public static void makeCodeNonBlockingEvenIfAPIsareSynchronous(String product) {
		long startTime = System.currentTimeMillis(), endTime = 0L;
		List<CompletableFuture<String>> futurePriceList = shopList.stream().map(s -> supplyAsync(() -> s.getName() + "..." + s.getPrice(product)))
				.collect(toList());
		List<String> priceListComputed = futurePriceList.stream().map(CompletableFuture::join).collect(toList());
		endTime = System.currentTimeMillis() - startTime;
		logger.info("Time computed  makeCodeNonBlockingEvenIfAPIsareSynchronous" + endTime + "....");
	}

	public static void thisWouldBeSequentialWay(String product) {
		long startTime = System.currentTimeMillis(), endTime = 0L;

		List<String> priceListComputed = shopList.stream().map(s -> supplyAsync(() -> s.getName() + "..." + s.getPrice(product))).map(CompletableFuture::join)
				.collect(toList());

		endTime = System.currentTimeMillis() - startTime;
		logger.info("Time computed thisWouldBeSequentialWay" + endTime + "....");
	}

	private static final Executor executor = Executors.newFixedThreadPool(Math.min(shopList.size(), 100), new ThreadFactory() {

		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(r);
			t.setDaemon(true);
			return t;
		}
	});

	public static void useExternalExecutorAsPerAppRequirementsAlongwithCompletableFuture(String product) {
		long startTime = System.currentTimeMillis(), endTime = 0L;

		List<CompletableFuture<String>> list = shopList.stream().map(shop -> supplyAsync(() -> shop.getName() + "..." + shop.getPrice(product), executor))
				.collect(toList());
		List<String> priceList = list.stream().map(CompletableFuture::join).collect(toList());
		endTime = System.currentTimeMillis() - startTime;
		logger.info("Time computed useExternalExecutorAsPerAppRequirementsAlongwithCompletableFuture " + endTime + "....");

	}

	public List<String> findPrices(String product) {
		return shopList.stream().map(shop -> shop.getPriceUpdated(product)).map(Quote::parse).map(quote -> Discount.applyDiscount(quote)).collect(toList());
	}

	public static List<String> findPricesUsingShopandDiscountServiceAysncUsingCompose(String product) {

		List<CompletableFuture<String>> futurePriceList =

				shopList.stream().map(shop -> supplyAsync(() -> shop.getPriceUpdated(product))).map(future -> future.thenApply(Quote::parse))
						.map(future -> future.thenCompose(quote -> supplyAsync(() -> Discount.applyDiscount(quote)))).collect(toList());

		return futurePriceList.stream().map(CompletableFuture<String>::join).collect(toList());

	}

	public Future<Double> useCombineOperationTogetResultfromTwoCompleteableFuture(Shop shop, String product) {

		return supplyAsync(() -> shop.getPrice(product)).thenCombine(supplyAsync(() -> ExchangeService.getRate(Money.USD, Money.EUR)),
				(price, rate) -> price * rate);
	}

	
	public void usingFuturePriortoJava8( Shop shop, String product) {
		ExecutorService executor = Executors.newCachedThreadPool();
		
		Future<Double> futureRate = executor.submit(new Callable<Double>() {

			@Override
			public Double call() throws Exception {
				return ExchangeService.getRate(Money.EUR, Money.USD);
			}
		});
		
		Future<Double> futurePriceInUSD = executor.submit(new Callable<Double>() {

			@Override
			public Double call() throws Exception {
				double price = shop.getPrice(product);
				return  price*futureRate.get();
			}
		});
				
	}

	public static Stream<CompletableFuture<String>> findPriceStream(String product) {
		return shopList.stream()
					   .map(shop-> supplyAsync(()->shop.getPriceUpdated(product)))
					   .map(future-> future.thenApply(Quote::parse))
					   .map(future -> future.thenCompose(quote-> supplyAsync(()->Discount.applyDiscount(quote))));
	}
	
	public static void getPricesFromShopsInstantlyWithoutWaitingforAlltoCompute(String product) {
		
		findPriceStream(product).map(f->f.thenAccept(System.out::println));
		
		CompletableFuture[] futures = findPriceStream(product).map(f->f.thenAccept(System.out::println))
		.toArray(size->new CompletableFuture[size]);
		
		CompletableFuture.allOf(futures).join();
		
		long start  = System.currentTimeMillis();
		
		findPriceStream(product).map(f -> f.thenAccept(
				s -> System.out.println(s + " (done in " + ((System.nanoTime() - start) / 1_000_000) + " msecs)")))
				.toArray(size -> new CompletableFuture[size]);
				
		futures = findPriceStream(product).map(future -> future.thenAccept(str-> System.out.println(str + " done in " + (currentTimeMillis()-start))))
		                        .toArray(size-> new CompletableFuture[size]);
		
		CompletableFuture.allOf(futures).join();
		
		String priceFromAnyShop = (String) CompletableFuture.anyOf(futures).join();
	}
	
	public static List<String> getPrices(String product) {
		return shopList.stream()
					   .map(shop-> shop.getPriceUpdated(product))
					   .map(Quote::parse)
					   .map(Discount::applyDiscount)
					   .collect(toList());
	}
	
	
	
	public static void main(String[] args) {

		// useAsyncApiForShop();

//		makeCodeNonBlockingEvenIfAPIsareSynchronous("My Product");
//		useExternalExecutorAsPerAppRequirementsAlongwithCompletableFuture("My Product");
//		thisWouldBeSequentialWay("My Product");
//		getPricesFromShopsInstantlyWithoutWaitingforAlltoCompute("My product");
//		findPricesUsingShopandDiscountServiceAysncUsingCompose("My product");
		System.out.println(getPrices("My Product"));
	}
}
