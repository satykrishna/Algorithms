package example.fp.dpvsfp;

public class MyLinkedList<T> implements MyList<T> {

	private T head;
	
	private MyList<T> tail;
	
	 public MyLinkedList(T head, MyList<T> tail) {
		 
		 this.head = head;
		 this.tail = tail;
	}
	
	@Override
	public T head() {
		return null;
	}

	@Override
	public MyList<T> tail() {
		return null;
	}

}
