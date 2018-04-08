package java8.defaultmethod.client;

import org.apache.log4j.Logger;

import java8.defaultmethod.resolutiontechniques.A;
import java8.defaultmethod.resolutiontechniques.B;
import java8.defaultmethod.resolutiontechniques.D;

public class ResolutionChecker extends D  implements B, A{
	
	public static final Logger logger = 
			Logger.getLogger(ResolutionChecker.class);


	public static void main(String[] args) {
		
		new ResolutionChecker().sayHello();
	}
	

}
