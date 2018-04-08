package java8.optional.client;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import static example.refactoring.java8.AppLogger.log;
import java8.optional.example.Person;

public class OptionalApp {

	private static final Logger logger = Logger.getLogger(OptionalApp.class);
	
	public static String getInsuranceName(Person person ) {
		return person.getCar().getInsurance().getName();
	}
	
	public static void main(String[] args) {
		log(logger, Level.INFO, ()->getInsuranceName(new Person()));
	}
} 
