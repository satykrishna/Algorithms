package example.java8.method;

import java.util.Arrays;
import java.util.List;

public class Game {

	public static void main(String[] args) {
		
		List<Resizable> resizableShapes = 
				Arrays.asList(new Square(), new Rectangle(), new Ellipse());
		
		System.out.println(resizableShapes);
	}
}