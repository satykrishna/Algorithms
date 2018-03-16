package example.java8.intro;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface BufferedReaderProcessor {

	public abstract String process(BufferedReader b) throws IOException;
}
