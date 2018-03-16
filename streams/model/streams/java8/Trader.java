package model.streams.java8;

public class Trader {

	private String name;
	
	private String city;

	public Trader() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Trader(String name, String city) {
		super();
		this.name = name;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return String.format("Trader [name=%s, city=%s]", name, city);
	}
	
	
	
}
