package books;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookApp {

	public static void main(String[] args) throws FileNotFoundException {

		List<Book> books = (ArrayList<Book>) Book.getList("books.csv");

		System.out.println("Number of books read in: " + books.size());
		System.out.println();

		System.out.println("Sorted book list:");

		Collections.sort(books);

		for (Book el : books) {
			System.out.println(el);
		}

		System.out.println();
		System.out.println("Reverse order:");

		Collections.reverse(books);
		

		for (Book el : books) {
			System.out.println(el);
		}
	}
}