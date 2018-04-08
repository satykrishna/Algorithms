package java8.defaultmethod.impl;

import java8.defaultmethod.v1.Moveable;
import java8.defaultmethod.v1.Rotatable;

public class Sun implements Moveable, Rotatable {

	public Sun() {
	}

	@Override
	public void setRotationAngle(int angleInDegrees) {

	}

	@Override
	public int getRotationAngle() {
		return 0;
	}

	@Override
	public int getX() {
		return 0;
	}

	@Override
	public int getY() {
		return 0;
	}

	@Override
	public void setX(int x) {

	}

	@Override
	public void setY(int y) {

	}

}
