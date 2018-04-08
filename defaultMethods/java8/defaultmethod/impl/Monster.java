package java8.defaultmethod.impl;

import java8.defaultmethod.v1.Moveable;
import java8.defaultmethod.v1.Resizable;
import java8.defaultmethod.v1.Rotatable;

public class Monster implements Resizable, Moveable, Rotatable {

	public Monster() {
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

	@Override
	public void setHeight(int height) {
	}

	@Override
	public void setWidth(int width) {
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public void setAbsoluteSize(int width, int height) {

	}

}
