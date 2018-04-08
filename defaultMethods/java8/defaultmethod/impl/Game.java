package java8.defaultmethod.impl;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import java8.defaultmethod.v1.Resizable;

public class Game {

	private Supplier<Utils> paintUtility = Utils::new;

	public  void drawShapes() {
		List<Resizable> shapes = 
				Arrays.asList(new Rectangle(),
							  new Square(),
							  new Elipse());
		paintUtility.get().paint(shapes);
	}


	private class Utils {
		public  void paint(List<Resizable> list )  {
			list.forEach(r -> {
				r.setAbsoluteSize(40, 40);
			});
		}
	}
}
