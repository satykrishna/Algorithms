package app.example.annotation;

import java.util.logging.Logger;

public class Employee {

	private static Logger logger = Logger.getLogger(Employee.class.getName());
	
	public void setSalary(double salary) {
		logger.info("Employee Salary is " + salary);
	}
}
