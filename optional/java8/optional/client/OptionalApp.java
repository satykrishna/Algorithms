package java8.optional.client;

import java.util.Map;
import java.util.Optional;
import java.util.Properties;

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

	
	private static Insurance findCheapInsurance(Person person, Car car) {
		//Logic to find Cheap Insurance
		Insurance cheapInsurance = new  Insurance();
		return cheapInsurance;
	}

	public static Optional<Insurance> nullSafeFindingCheapInsurance(
			Optional<Person> optionalPerson, Optional<Car> optionalCar) {
		return optionalPerson.flatMap(person-> optionalCar.flatMap(car->Optional.ofNullable(findCheapInsurance(person, car))));
	}
	
	private static void useOptionalsToAvoidNullChecks() {
		Insurance insurance = new Insurance();
		Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
		Optional<String> insuranceName = optInsurance.map(Insurance::getName);
	}
	
	private static String getInsuranceforPersonOfAboveCertainAge(Person person, int minAge) {
		return Optional.ofNullable(person)
					   .filter(p -> p.getMinAge() > minAge)
					   .flatMap(Person::getCarAsOptional)
					   .flatMap(Car::getInsuranceAsOptional)
					   .map(Insurance::getName)
					   .orElse("UNKNOWN");
	}

	
	public static Optional wrappingAPotentialNullValueinOptional(Map<String, String> map, String key) {
	   return Optional.ofNullable(map.get(key));
	}
	
	
	public static Optional<Integer> stringToInt(String str) {
		try {
			return Optional.of(Integer.parseInt(str));
		}
		catch(NumberFormatException exception) {
			return Optional.empty();
		}
	}
	
	public static int readDuration(Properties properties, String name ) {
		return OptionalApp.stringToInt( properties.getProperty(name)).filter(i->i > 0).orElse(0);
	}
	
	public static int readDurationOtherWay(Properties properties, String name ) {
		 return  	Optional.ofNullable((String)properties.get(name))
		  			.flatMap(OptionalApp::stringToInt)
		   			.filter(i->i>0)
		   			.orElse(0);
	}
	
	
	public static void main(String[] args) {
		differentKindsOfOptionals();
		useOptionalsToAvoidNullChecks();
		usingOptionalsToFilter(null);
	}

	private static void usingOptionalsToFilter(Insurance insurance) {
		
		Optional.ofNullable(insurance)
				.filter(i->"CAMBRIDGE".equals(i.getName()))
				.ifPresent(System.out::println);
		
	}

}
