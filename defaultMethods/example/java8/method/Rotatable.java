package example.java8.method;

public interface Rotatable {

	public abstract void setRotationAngle(int angleInDegrees);
	
	public abstract int getRotationAngle();
	
	public default void rotateBy(int angleInDegrees) {
		setRotationAngle((getRotationAngle()+angleInDegrees)%360);
	}
}
