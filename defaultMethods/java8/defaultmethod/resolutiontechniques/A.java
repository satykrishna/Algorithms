package java8.defaultmethod.resolutiontechniques;

import org.apache.log4j.Logger;

public interface A {
	
	public static final Logger logger = 
			Logger.getLogger(A.class);


	default void sayHello() {
		logger.info("Hello from A !!!");
	}
	
}
