package java8.lambda.example;

import java.io.BufferedReader;

@FunctionalInterface
public interface FileProcessor {

	public abstract String process(BufferedReader bufferedReader);

}
