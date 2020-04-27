package books;

import java.util.*;
import java.io.*;

/**
 * This Class creates an object of Book. The book has a title, author, and year
 * published. The class also has a method of get List that gets a list of books
 * from a .csv file.
 * 
 * @author Carlos Martinez
 */
public class Book implements Comparable<Book> {

	// Fields

	/**
	 * This is the Title of the book
	 */
	private String title;

	/**
	 * This is the name of the author who wrote the book
	 */
	private String author;

	/**
	 * This is the year the book was published
	 */
	private int year;

	// Constructor
	/**
	 * Creates and object of a book.
	 * @param title The title of the book.
	 * @param author The author who wrote the book.
	 * @param year The year the book was published
	 */
	public Book(String title, String author, int year) {
		this.title = title;
		this.author = author;
		this.year = year;
	}
	
	/**
	 * Returns the title of the book.
	 * @return The title of the book.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the name of the author who wrote the book.
	 * @return The name of the author who wrote the book.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Returns the year the book was published
	 * @return The year the book was published
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * From the interface Comparable, it compares the books
	 * based off the title of the book.
	 */
	@Override
	public int compareTo(Book other) {
		int index = title.compareTo(other.title);
		return index;
	}
	
	/**
	 * Override toString method that display the book as
	 * title by author (year).
	 */
	@Override
	public String toString() {
		return getTitle() + " by " + getAuthor() + " (" + getYear() + ")";
	}
	
	/**
	 * This method reads from a something.csv file and creates a list of
	 * books from it. The file must be organized by title,author,year or
	 * the file will not be able to organize and add the book to the list.
	 * @param file The name of the file it will read.
	 * @return A list of books it created while reading something.csv file.
	 * @throws FileNotFoundException If the file is not found this exception
	 * will be thrown or catch.
	 */
	public static List<Book> getList(String file) throws FileNotFoundException {
		List<Book> books = new ArrayList<Book>();
		
		try (Scanner input = new Scanner(new File(file))) {
			
			String line;
			
			while(input.hasNextLine()){
				line = input.nextLine();
				String[] nextLine = line.split(",");
				
				if(nextLine.length != 3){
					System.out.println("Problem reading in \""+ line + "\"");
				}
				if(nextLine.length == 3){
					books.add(new Book(nextLine[0],nextLine[1],Integer.parseInt(nextLine[2])));
				}
			}
				
			}catch (FileNotFoundException e) {
				System.out.println("File " + file + " could not be found");
			}
		
		return books;

	}
}
