package java8.defaultmethod.v1;

public interface Moveable {

	public abstract int getX();

	public abstract int getY();

	public abstract void setX(int x);

	public abstract void setY(int y);

	default void moveHorizontally(int distance) {
		setX(getX() + distance);
	}

	default void moveVertically(int distance) {
		setY(getY() + distance);
	}

}
