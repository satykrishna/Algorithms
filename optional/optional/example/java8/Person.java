package optional.example.java8;

import java.util.Optional;

public class Person {

	
	private Car car;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	public Optional<Car> getOptionalCar() {
		return Optional.ofNullable(car);
	}
	
	
	
	public Insurance findCheapInsurance(Person person, Car car) {
		Insurance cheapInsurance = new Insurance();
		
		Optional<Insurance> optInsurance = Optional.ofNullable(cheapInsurance);
		
		optInsurance.filter(insurance->insurance.getName().equalsIgnoreCase("myInsurance"))
					.ifPresent(x->System.out.println("Hi"));
		
		return cheapInsurance;
	}
	
	
	public Optional<Insurance> findOptonalInsurance(Optional<Person> person, Optional<Car> car) {
		
		if(person.isPresent() && car.isPresent()) {
			return Optional.ofNullable(findCheapInsurance(person.get(), car.get()));
		}
		
		return Optional.empty();
	}
	
	public Optional<Insurance> nullSafeInsurance(Optional<Person> person, Optional<Car> car) {
		return person.flatMap(actual -> car.map(c ->findCheapInsurance(actual, c)));
	}
	
	private int age;
	
	
	public String getCarInsuranceName(Person person, int minAge) {
		
		return Optional.ofNullable(person).filter(p-> p.age >=minAge)
				                         .flatMap(p->p.getOptionalCar())
				                         .flatMap(c->c.getInsurance())
				                         .map(i->i.getName())
				                         .orElse("UNKNOWN");
											
	}
	
	
	
	
	

}
