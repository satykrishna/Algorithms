package example.fp.dpvsfp;

public interface MyList<T> {
	
	public abstract T head();
	
	public abstract MyList<T> tail();
	
	public default boolean isEmpty() {
		return true;
	}
	
	
	
	

}
