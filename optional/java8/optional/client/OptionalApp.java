package java8.optional.client;

import java.util.Optional;

import org.apache.log4j.Logger;

import java8.optional.example.Car;
import java8.optional.example.Insurance;
import java8.optional.example.Person;

public class OptionalApp {

	private static final Logger logger = Logger.getLogger(OptionalApp.class);

	private static Car car;

	private static void differentKindsOfOptionals() {

		Optional<Car> emptyCar = Optional.empty(); // returns an empty optional
													// value.

		Optional<Car> optCar = Optional.of(car); // cannot be null, if it is, it
													// throws null pointer
													// exception

		Optional<Car> canbeANullCar = Optional.ofNullable(car);
	}

	public static Optional<String> getInsuranceName(Person person) {
		Optional<Person> optPerson = Optional.of(person);
		return optPerson.flatMap(Person::getCarAsOptional).
				flatMap(Car::getInsuranceAsOptional).
				map(Insurance::getName);
	}

	
	private static void useOptionalsToAvoidNullChecks() {

		Insurance insurance = new Insurance();

		Optional<Insurance> optInsurance = Optional.ofNullable(insurance);

		Optional<String> insuranceName = optInsurance.map(Insurance::getName);

	}

	public static void main(String[] args) {

		differentKindsOfOptionals();

		useOptionalsToAvoidNullChecks();
	}

}
