package java8.defaultmethod.v1;

public interface Rotatable {
	
	public abstract void setRotationAngle(int angleInDegrees);
	
	public abstract int getRotationAngle();
	
	default void rotateBy(int angleInDegrees) {
		setRotationAngle((getRotationAngle()+angleInDegrees)%360);
	}

}
