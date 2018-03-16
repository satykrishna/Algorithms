package java8.lambda.functionalInterface;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface BufferedReaderProcessor {
	
	public abstract String process(BufferedReader reader) throws IOException;
}
