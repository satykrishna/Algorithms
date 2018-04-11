package java8.optional.example;

import java.util.Optional;

public class Person {
	
	private Car car;
	
	private int minAge;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Optional<Car> getCarAsOptional() {
		return Optional.ofNullable(car);
	}

	public int getMinAge() {
		return minAge;
	}

	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}
	

}
