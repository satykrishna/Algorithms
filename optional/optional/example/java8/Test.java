package optional.example.java8;

import java.util.Optional;
import java.util.Properties;

public class Test {

	private static Properties properties;

	static {
		properties = new Properties();
		properties.setProperty("a", "5");
		properties.setProperty("b", "b");
		properties.setProperty("c", "4");
	}

	public static Optional<Integer> stringToInt(String str) {
		Optional<Integer> optInt = null;
		try {
			optInt = Optional.of(Integer.parseInt(str));
		} catch (NumberFormatException e) {
			optInt = Optional.empty();
		}
		return optInt;
	}

	public static int propertyRead(String name) {
		String value = properties.getProperty(name);

		if (value == null)
			return 0;

		int i = 0;

		try {
			i = Integer.parseInt(value);
			if (i > 0) {
				return i;
			}
		} catch (NumberFormatException e) {

		}
		return i;
	}

	public static int readPropertiesUsingOptions(String name) {
		return Optional.ofNullable(properties.getProperty(name)).flatMap(value -> stringToInt(value))
				.filter(i -> i >= 0).orElse(0);
	}

	public static String getCarInsurance(Person person) {
		return Optional.ofNullable(person).flatMap(Person::getOptionalCar).flatMap(Car::getInsurance)
				.map(Insurance::getName).orElse("UNKNOWN");
	}

	public static void createOptionalObjects() {

		Optional<Car> optionalCar = Optional.empty(); // create an empty
														// optional object

		Car car = new Car();

		Optional<Car> optCar = Optional.of(car);

		car = null;

		optCar = Optional.of(car);

		optCar = Optional.ofNullable(car);

		String name = null;

		Insurance insurance = null;

		if (insurance != null) {
			name = insurance.getName();
		}

		Optional<Insurance> optInsurance = Optional.ofNullable(insurance);

		Optional<String> insuranceName = optInsurance.map(Insurance::getName);

	}
}
