package app.example.annotation;

import java.io.IOException;

public class PolicyTestCases {

	public PolicyTestCases() {
	}

	@TestCase(willThrow=IOException.class)
	public static void testCase1() {
		
	}
	
	@TestCase()
	public static void testCase2() {
		
	}
}
