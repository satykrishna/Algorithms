package java8.lambda.examples;

import java.io.BufferedReader;
import java.io.IOException;

public interface BufferReadProcessor {
	
	public abstract String processGivenFile(BufferedReader reader) throws IOException;

}
