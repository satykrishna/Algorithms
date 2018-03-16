package example.java8.method;

public interface Moveable {

	public abstract int getX();
	
	public abstract int getY();
	
	public abstract void setX(int x);
	
	public abstract void setY(int y);
	
	public default void moveHorizontally(int distance) {
		int newX = getX() + distance;
		setX(newX);
	}
	
	public default void moveVertically(int distance) {
		setY(getY()+distance);
	}
	
}
