/*************************************
 * Author: Carlos Humberto Martinez Guadarrama
 * Date: January 13, 2017
 * Assignment: Student
 ************************************/

package students;
/**
 * This class creates an object of Student. The 
 * class gives the personal information of the
 * student and creates a unique sNumber for them.
 * @author Carlos Martinez
 */
public class Student {
	//Fields
	
	/**
	 * This is the student's first name.
	 */
	private String firstName;
	
	/**
	 * This is the student's last name.
	 */
	private String lastName;
	
	/**
	 * This is the the personal and unique S number of the
	 * student.
	 */
	private int sNumber;
	
	/**
	 * This is the the student's major.
	 */
	private String major;
	
	/**
	 * This is the student's GPA.
	 */
	private double gpa;
	
	/**
	 * This counter keeps track of the number of students
	 * that already exist so there S number is never duplicated. 
	 */
	private static int count;
	
	//Constructor
	/**
	 * This is a constructor that helps create an object
	 * of student with no fields required.
	 */
	public Student() {
		this.sNumber = 1234567 + count++;
	}
	
	/**
	 * This is a constructor that helps create an object
	 * of student 
	 * @param fName This is the student's first name.
	 * @param lName This is the student's last name.
	 * @param maj This is the student's major declared.
	 * @param gpa This is the student's GPA earned.
	 */
	public Student(String fName, String lName, String maj, double gpa) {
		this.firstName = fName;
		this.lastName = lName;
		this.major = maj;
		this.gpa = gpa;
		this.sNumber = 1234567 + count++;
	}
	
	// Methods
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getsNumber() {
		return sNumber;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	@Override
	public String toString() {
		return "s" + getsNumber() + " " + getFirstName() + " " +
			   getLastName() + " " + getMajor() + " gpa:" + getGpa();
	}
}