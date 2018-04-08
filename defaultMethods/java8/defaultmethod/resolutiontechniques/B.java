package java8.defaultmethod.resolutiontechniques;

import org.apache.log4j.Logger;

public interface B extends A {
	
	
	public static final Logger logger = Logger.getLogger(B.class);

	default void sayHello() {
		logger.info("Hello from B !!!");
	}
}
