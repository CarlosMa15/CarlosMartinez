package inputOutput;

import java.io.File;

public class DemoClassFile {

	public static void main(String[] args) {
		String absolutePath = "C:/Text";
		
		File file = new File(absolutePath);
		
		System.out.println("The Path " + file + (file.exists() ? " exists" : " does not exist"));
		
		if (file.isDirectory()) {
			for(String el: file.list()) {
				System.out.println(el);
			}
		}
	}
}