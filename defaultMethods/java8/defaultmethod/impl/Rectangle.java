package java8.defaultmethod.impl;

import java8.defaultmethod.v1.Resizable;

public class Rectangle implements Resizable {
	
	private int height, width;

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	public Rectangle() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void setAbsoluteSize(int width, int height) {
		
	}
	


}
