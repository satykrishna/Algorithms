package java8.optional.example;

import java.util.Optional;

public class Person {
	
	private Car car;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Optional<Car> getCarAsOptional() {
		return Optional.ofNullable(car);
	}
	

}
