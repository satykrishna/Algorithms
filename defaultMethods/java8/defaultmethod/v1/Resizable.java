package java8.defaultmethod.v1;

public interface Resizable {

	public abstract void setHeight(int height);

	public abstract void setWidth(int width);

	public abstract int getHeight();

	public abstract int getWidth();

	public abstract void setAbsoluteSize(int width, int height);

	default void setRelativeSize(int wFactor, int hFactor) {
		setAbsoluteSize(getWidth() / wFactor, getHeight() / hFactor);
	}

}
