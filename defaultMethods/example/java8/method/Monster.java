package example.java8.method;

public class Monster implements Resizable, Moveable, Rotatable {

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRotationAngle(int angleInDegrees) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getRotationAngle() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setWidth(int width) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHeight(int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAbsoluteSize(int width, int height) {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args) {
		Monster m  = new  Monster();
		m.rotateBy(180);
		m.moveVertically(10);
	}

}
