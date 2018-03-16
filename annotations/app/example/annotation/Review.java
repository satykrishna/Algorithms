package app.example.annotation;

import app.example.annotation.Review.ReviewStatus;

public @interface Review {
	
	String comments() default "";
	
	ReviewStatus status() default ReviewStatus.PENDING;
	
	public enum ReviewStatus {
		PENDING,
		FAILED,
		PASSED,
		PASSEDWITHCHANGES
	}

}


@Review(comments="Tests failed", status = ReviewStatus.FAILED)
class Test {
	
}