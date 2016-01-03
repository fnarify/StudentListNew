package comp125;

/**
 * Class to provide a demo of the class 'StudentList'.
 * 
 * Name: Bao-lim Smith
 * ID:   43277047
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentListDemo 
{

	/**
	 * Main program.
	 */
	public static void main(String[] args) 
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Welcome to the student list program.");
		
		// Initialises the number of students in the cohort, and space for them.
		System.out.println("Please enter the amount of students in the cohort:");
		
		// Performs the error checking on the number of students in the cohort.
		int numStud = 0;
		numStud = giveNumStudents(numStud, keyboard);
		
		// Creates the student list.
		StudentList theList = new StudentList(numStud);
	
		// Should prompt user to enter data for each individual student, and then display it unordered.
		theList.readList();
		System.out.println("This is all the students in the cohort, and their repsective marks:");
		theList.displayList();
		
		// Option variables (initialisation).
		int choice = 0; 
		int choiceOld = 0; // Helps prevent multiple displayOptions() being shown at once.
		boolean sortedAlphabetical = false;
		boolean meritSorted = false;
		boolean end = false; // Keeps the program running indefinitely, must always be false.
		StudentList meritList = null; // The list that is sorted by merit.
		
		// Displays the options provided to the user.
		displayOptions(); 
		
		// Determines the value of choice, and checks if it is valid.
		choice = checkChoice(choice, keyboard);
		
		while (!end) // Helps if the same choice is selected more than once.
		{
			// Decides which method to perform.
			decideOptions(choice, choiceOld, sortedAlphabetical, meritSorted, meritList, theList, keyboard);
			
			// Prevents errors (the value of either boolean resetting).
			if (choice == 1 && !sortedAlphabetical)
				sortedAlphabetical = true;
			else if (choice == 3 && !meritSorted)
				meritSorted = true;
			
			// Only if the same choice was selected.
			displayOptions();
			choice = checkChoice(choice, keyboard);
		}

	}
	
	/**
	 * Displays the list of options to the user.
	 */
	public static void displayOptions()
	{
		System.out.println(); // Break
		// Options
		System.out.println("What option would you like to view?");
		System.out.println("(Press the number corresponding to your choice and then hit enter)");
		System.out.println(); // Break
		
		System.out.println("1. Sort the students in alphabetical order.");
		
		System.out.println("2. Display the average score of the cohort.");

		System.out.println("3. Sort the students by merit.");

		System.out.println("4. Retrieve the marks of an individual student. (Only once sorted alphabetically)");

		System.out.println("5. end the program.");
	}	
	
	/**
	 * Method that decides which option the user has selected, and then performs
	 * the required action related to that method.
	 */
	public static void decideOptions(int choice, int choiceOld, boolean sortedAlphabetical, boolean meritSorted,
			                         StudentList meritList, StudentList theList, Scanner keyboard)
	{
		while (choice != choiceOld)
		{
			/**
			 * Sorts and then displays the list by alphabetical order.
			 * If already sorted in alphabetical order, merely displays the list.
			 */
			if (choice == 1 && !sortedAlphabetical)
			{
				theList.sortByName();
				sortedAlphabetical = true;

				System.out.println("This is the alphabetical list:");
				theList.displayList();

				displayOptions();

				// Helps to make the options appear only once.
				choiceOld = choice;
				choice = checkChoice(choice, keyboard);
			}
			else if (choice == 1 && sortedAlphabetical)
			{
				System.out.println("This is the alphabetical list:");
				theList.displayList();
				
				displayOptions();

				choiceOld = choice;
				choice = checkChoice(choice, keyboard);
			}

			/**
			 * Computes and displays the average score of the cohort as a double.
			 */
			else if (choice == 2)
			{					
				System.out.println("The average score of the cohort is: " + theList.averageScore());
				
				displayOptions();

				choiceOld = choice;
				choice = checkChoice(choice, keyboard);
			}
			
			/**
			 * Shows the list by decreasing marks, or merit.
			 * If already sorted by merit previously, just displays the already sorted list.
			 * 
			 * this creates a deep copy to sort by merit, so there will be two list that are
			 * individually maintained.
			 */
			else if (choice == 3 && !meritSorted)
			{
				// Creates the deep copy.
				meritList = theList.deepCopy(); 

				// Performs these actions on the deep copy 'meritList' as to preserve 'theList'.
				meritList.meritSort();
				meritSorted = true;

				System.out.println("The list sorted by merit:");
				meritList.displayList();

				displayOptions();

				choiceOld = choice;
				choice = checkChoice(choice, keyboard);
			}
			else if (choice == 3 && meritSorted)
			{
				System.out.println("The list sorted by merit:");
				meritList.displayList();

				displayOptions();

				choiceOld = choice;
				choice = checkChoice(choice, keyboard);
			}
			
			/**
			 * Exits the program
			 */
			else if (choice == 5)
				System.exit(0); // End program.	
			
            /**
             * Continuously allows the user to enter input to find a student's individual marks.
             * Will consistently repeat this action until the user types 'exit'. Upon which 
             * the user is taken back to the option screen.
             * 
             * If the list hasn't been sorted alphabetically before using this option,
             * a prompt will tell the user to sort it alphabetically, and then take the
             * user back to the options screen.
             */
			while (choice == 4)
			{
				if (sortedAlphabetical)
				{
					System.out.println("Please enter the name of the student whose mark you wish to view: ");
					System.out.println("(If you wish to stop searching for a student's mark, then type 'exit'"
							           + " and hit enter)");
					String givenName = keyboard.next();

					// Displays the score of an individual student, or exits the option.
					if (givenName.equals("exit"))
					{
						displayOptions();
						
						choice = choiceOld;
						choice = checkChoice(choice, keyboard);
						
						break; // Exits current option.
					}
					else if (theList.retrieveScore(givenName) == -1)
						System.out.println("This student is not part of the cohort.");
					else
						System.out.println(givenName + "'s score is: " + theList.retrieveScore(givenName));	

					System.out.println(); // Break.
				}
				else
				{
					System.out.println("You must first sort the cohort alphabetically, before finding an "
							+ "individual student's mark.");

					displayOptions();

					choiceOld = choice;
					choice = checkChoice(choice, keyboard);
					break; // Exits out of option.
				}
			}
			
		}
	}
	
	/**
	 * Performs error checking on the value of choice, 
	 * and prevents InputMismatchExceptions.
	 * 
	 * Must be between 1 and 5.
	 */
	public static int checkChoice(int choice, Scanner keyboard)
	{
		boolean valid = false;
		
		// Checks that the choice is valid, in relation to the options.
		while (!valid)
		{
			try
			{
				choice = keyboard.nextInt();
				
				while (choice < 1 || choice > 5)
				{
					System.out.println("This is not a valid choice, please enter a valid choice"
							           + " (between 1 and 5):");
					displayOptions();
					choice = keyboard.nextInt();
				}
				
				valid = true;
			}
			catch(InputMismatchException e)
			{
				System.out.println("This is not valid, please enter a valid (integer) choice:");
				displayOptions();
				keyboard.nextLine(); // Rubbish eradication.
			}
		}
		
		// Will return the valid choice.
		return choice;
	}
	
	/**
	 * Performs error checking on numStud, to make sure the user
	 * enters in a correct error; and prevent application crashing.
	 * 
	 * Must be greater than 0. 
	 */
	public static int giveNumStudents(int numStud, Scanner keyboard)
	{
		boolean validInput = false;
		
		// Prevents minor errors with the input (e.g. If a non-integer is entered).
		while(!validInput)
		{	
			try
			{
				numStud = keyboard.nextInt();
				
				// More error checking.
				while (numStud <= 0)
				{
					System.out.println("Please enter a valid number of students (greater than zero):");
					numStud = keyboard.nextInt();
				}
				validInput = true;
			} 
			catch(InputMismatchException e)
			{
				System.out.println("Please enter a valid (integer) amount of students:");
				keyboard.nextLine(); // Prevents rubbish.
			}
		}
		
		// Returns a valid number of students.
		return numStud;
	}
	
}
