package example.java8.intro;

public class Lambda {

	public static void process(Runnable r) {
		r.run();
	}
	
	public static void main(String[] args) {
		
		Runnable r1 = ()->System.out.println("Hello World 1");
		
		process(()->System.out.print("Hello world 2"));
	}

}
