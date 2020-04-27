/*************************************
 * Author: Carlos Humberto Martinez Guadarrama
 * Date: January 13, 2017
 * Assignment: Student
 ************************************/

package students;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class has all the functionality of the of the program that allows you to
 * track and get all the personal information of the students.
 * 
 * @author Carlos Martinez
 */
public class StudentApp {

	static final Scanner input = new Scanner(System.in);

	static ArrayList<Student> students = new ArrayList<>();

	static int sNumber = 0;

	public static void main(String[] args) {

		// 3 default students
		Student john = new Student("John", "Smith", "(CS)", 3.6);
		Student lauren = new Student("Lauren", "Edwards", "(CS)", 3.8);
		Student alex = new Student("Alex", "Taylor", "(EE)", 3.2);

		// ArrayList of students and initialize it with 3 different students
		students.add(john);
		students.add(lauren);
		students.add(alex);

		int selection = 0;

		do {
			System.out.println();
			displayMenu();
			selection = input.nextInt();
			System.out.println();

			switch (selection) {
			case 1:
				addStudent();
				break;
			case 2:
				findStudent();
				break;
			case 3:
				deleteStudent();
				break;
			case 4:
				displayStudents();
				break;
			case 5:
				displayNumberStudents();
				break;
			case 6:
				exit();
				break;
			}

		} while (selection != 6);
	}

	/**
	 * This method says Good Bye to the user.
	 */
	private static void exit() {
		System.out.print("Good Bye");

	}

	/**
	 * This method lets the user know the number of students that exist
	 */
	private static void displayNumberStudents() {
		System.out.println("Number of Students:" + students.size());

	}

	/**
	 * This method display all students in a list.
	 */
	private static void displayStudents() {
		for (Student el : students) {
			System.out.println(el);
		}

	}

	/**
	 * This method delete a student based off their sNumber.
	 */
	private static void deleteStudent() {
		System.out.print("Delete student with sNumber S");
		sNumber = input.nextInt();
		int index = 0;
		int checker = 0;

		if (students.size() != 0) {

			for (int i = 0; i < students.size(); i++) {

				if (students.get(i).getsNumber() == sNumber) {
					System.out.println("S" + students.get(i).getsNumber() + " " + students.get(i).getFirstName() + " "
							+ students.get(i).getLastName() + " has been deteted");
					index = i;
					checker++;

				}
			}
			if (checker == 1) {
				students.remove(index);
			}

		}

		if (students.size() == 0 || checker == 0) {
			System.out.println("student could not be found");
		}

	}

	/**
	 * This method finds a student from the database based of the student
	 * sNumber.
	 */
	private static void findStudent() {
		System.out.print("Find student by sNumber S");
		sNumber = input.nextInt();
		int checker = 0;
		for (Student el : students) {

			if (el.getsNumber() == sNumber) {
				System.out.println(el);
				checker++;
			}
		}
		if (checker == 0) {
			System.out.println("student could not be found");
		}

	}

	/**
	 * This method adds a student to the list with the input of of the user.
	 */
	private static void addStudent() {
		System.out.print("First name: ");
		input.nextLine();
		String fName = input.nextLine();
		System.out.print("Last name: ");
		String lName = input.nextLine();
		System.out.print("Major:");
		String major = input.nextLine();
		System.out.println("GPA: ");
		double gpa = input.nextDouble();

		students.add(new Student(fName, lName, major, gpa));

	}

	/**
	 * This method displays the menu and nothing else.
	 */
	private static void displayMenu() {

		System.out.println("1. Add a student");
		System.out.println("2. Find a student");
		System.out.println("3. Delete a student");
		System.out.println("4. Display all Students");
		System.out.println("5. Display the total number of students");
		System.out.println("6. Exit");
		System.out.print("Enter your selection: ");

	}
}