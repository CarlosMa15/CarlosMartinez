package labKeyValuePair1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PairApp {
	
	public static void main(String[] args) {
		KeyValuePair<String,Integer> p1 = new KeyValuePair<>("SLC",189899);
		KeyValuePair<String,Integer> p2 = new KeyValuePair<>("NY",8244910);
		
		System.out.println(p1);
		System.out.println(p2);
		System.out.println("Does p1 equal p2: " + p1.equals(p2));
		System.out.println();
		
		//p1 = p2;
		System.out.println(p1);
		System.out.println(p2);
		System.out.println("Does p1 equal p2: " + p1.equals(p2));
		System.out.println();
		System.out.println();
		
		KeyValuePair<String,Integer> p3 = new KeyValuePair<>("LA",3819702);
		KeyValuePair<String,Integer> p4 = new KeyValuePair<>("SF",812826);
		
		List<KeyValuePair<String,Integer>> cities = new ArrayList<>();
		Collections.addAll(cities, p1,p2,p3,p4);
		
		System.out.println("Original List:");
		for (KeyValuePair<String,Integer> el: cities) {
			System.out.println(el.getKey() + ": " + el.getValue());
		}
		System.out.println();
		
		System.out.println("Sorted List:");
		Collections.sort(cities);
		for (KeyValuePair<String,Integer> el: cities) {
			System.out.println(el.getKey() + ": " + el.getValue());
		}
	}
}