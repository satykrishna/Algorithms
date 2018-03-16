package app.example.annotation;

public @interface Name {

	String firstName() default "";
	String lastName()  default "";
}
