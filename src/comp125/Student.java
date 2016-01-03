package comp125;

/**
 * Class to store family name and exam score data for a student.
 * @author Scott McCallum, September 2013 (updated 26 Sept.)
 */
	
import java.util.Scanner;

public class Student {
	private String name;  // family name
	private double score;  // exam score
	
	/**
	 * Default constructor.
	 */
	public Student() {
		name = "";
		score = 0.0;
	}
	
	/**
	 * Constructor with two parameters.
	 */
	public Student(String aName, double aScore) {
		name = aName;
		score = aScore;
	}
	
	/**
	 * Return true if this student equals aStudent, false otherwise.
	 */
	public boolean equals(Student aStudent) {
		return name.equals(aStudent.name) && score == aStudent.score; 
	}
	
	/**
	 * Read student data.
	 */
	public void readStudent() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please enter their family name:");
		name = keyboard.nextLine();
		System.out.println("Please enter their respective exam score:");
		score = keyboard.nextDouble();
	}
	
	public String getName() {
		return name;
	}
	
	public double getScore() {
		return score;
	}

}
