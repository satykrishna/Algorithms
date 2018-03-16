package example.java8.method;

public interface Resizable extends Drawable {

	public abstract int getWidth();
	
	public abstract int getHeight();
	
	public abstract void setWidth(int width);
	
	public abstract void setHeight(int height);
	
	public abstract void setAbsoluteSize(int width, int height);
	
	public default void setRelativeSize(int wFactor, int hFactor) {
		setAbsoluteSize(getWidth()/wFactor, getHeight()/hFactor);
	}

}
