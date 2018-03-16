package java8.lamba.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class InbuiltLambdasEx {
	
	//predicate example
	public static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> filterList = new ArrayList<T>();
		for(T t : list) {
			if(p.test(t)) {
				filterList.add(t);
			}
		}
		return filterList;
	}
	
	//consumer example
	public static <T> void forEach(List<T> list, Consumer<T> c) {
		for(T t : list) {
			c.accept(t);
		}
	}
	
	//Function example
	public static <E, K> List<K> map(List<E> list, Function<E, K> function) {
		List<K> transformList = new ArrayList<K>();
		for(E e : list) {
			transformList.add(function.apply(e));
		}
		return transformList;
	}
	

}
