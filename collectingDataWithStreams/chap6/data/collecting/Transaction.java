package chap6.data.collecting;

public class Transaction {

	private String currency;
	private String city;
	private boolean isExpensive;
	private int value;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean isExpensive() {
		return isExpensive;
	}

	public void setExpensive(boolean isExpensive) {
		this.isExpensive = isExpensive;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Transaction(String currency, String city, boolean isExpensive, int value) {
		super();
		this.currency = currency;
		this.city = city;
		this.isExpensive = isExpensive;
		this.value = value;
	}

	public Transaction() {
		super();
	}
	
}
