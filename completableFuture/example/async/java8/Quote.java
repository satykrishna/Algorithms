package example.async.java8;

public class Quote {

	private String shopName;

	private double price;

	private Discount.Code discountCode;

	public Quote(String shopName, double price, Discount.Code discountCode) {
		this.shopName = shopName;
		this.price = price;
		this.discountCode = discountCode;
	}

	public static Quote parse(String str) {
		String[] split = str.split(":");
		String shopName = split[0];
		double price = Double.parseDouble(split[1]);
		Discount.Code code = Discount.Code.valueOf(split[2]);
		return new Quote(shopName, price, code);
	}

	public String getShopName() {
		return shopName;
	}

	public double getPrice() {
		return price;
	}

	public Discount.Code getDiscountCode() {
		return discountCode;
	}

}
