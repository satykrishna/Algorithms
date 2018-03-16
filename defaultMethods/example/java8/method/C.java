package example.java8.method;

public class C implements A, B {
	
	@Override
	public void hello() {
		System.out.println("Hello from C");
	}

	public static void main(String[] args) {
		
		new C().hello();
	}
}
