package comp125;

/**
 * Class to store and provide functionality for a list
 * of exam results, for a cohort of students.
 * 
 * Name: Bao-lim Smith
 * ID:   43277047
 */
public class StudentList 
{
	private int numStudents; // This is the number of students in the cohort.
	                         // It is equal to list.length, and is provided for convenience.
	private Student [] list; // This is the array of Student objects which stores
	                         // all the data for the student cohort.
	
	/**
	 * Default constructor.
	 */
	public StudentList() {	
		
	}
	/**
	 * Constructor with one parameter.
	 * @param numStud
	 */
	public StudentList(int numStud) 
	{
		list = new Student[numStud];
		numStudents = numStud;
	}
	
	/**
	 * Get student at position i.
	 */
	public Student getAt(int i) 
	{
		return list[i];
	}
	
	/**
	 * Set element at position i to aStudent.
	 */
	public void setAt(int i, Student aStudent) 
	{
		list[i] = aStudent;
	}
	
	/**
	 * Read list of results for the student cohort.
	 */
	public void readList() 
	{
		Student theStudent;
		for (int i = 0; i < numStudents; i++)
		{
			System.out.println("Please enter data for the next student.");
			theStudent = new Student();
			theStudent.readStudent();
			list[i] = theStudent;
		}	
	}
	
	/**
	 * Display list of results for the student cohort.
	 */
	public void displayList() 
	{
		for (int i = 0; i < numStudents; i++)
		{
			System.out.printf("%-15s",  list[i].getName());
			System.out.printf("%5.1f", list[i].getScore());
			System.out.println();
		}
	}
	
	/**
	 * Sort list into alphabetical order on name.
	 * Uses insertion sort algorithm.
	 * 
	 * Question 1. Insertion sort could be more efficient than some other
	 * sorting algorithms for the purpose intended, all things considered. 
	 * Do you agree and if so why? Write 6 to 10 lines, say.
	 */
	
	/**
	 * From the two sorting algorithms that has been covered, 
	 * insertion sort seems the most efficient. 
	 * As for selection sort the worst, and best case, time complexity 
	 * is always O(n^2). While for insertion sort, the best case is O(n)
	 * and the worst case O(n^2). 
	 * 
	 * Since for the specifications required in this program, the 
	 * list may be close to being sorted, insertion sort becomes 
	 * the clear winner. 
	 * When it comes to implementation both are relatively easy to 
	 * implement within the program specifications.
	 */
	public void sortByName() 
	{
		int j;
		// String to check against.
		String toCompare;
		
		for (int i = 1; i < numStudents; i++)
		{
			// Stores the element to be inserted.
			Student insert = list[i];
			
			// Argument string to check with.
			String temp = insert.getName();
			
			j = i - 1;
			toCompare = list[j].getName();
			
			// Checks whether temp is lexicographically lower than initial,
			// if so moves the elements up accordingly.
			while (j >= 0 && temp.compareToIgnoreCase(toCompare) < 0)
			{
				list[j + 1] = list[j];
			    j--;
				
			    // Helps to prevent errors.
			    if (j >= 0)
			    	toCompare = list[j].getName();
			}
						
			// Inserts the element to the sorted position.
			list[j + 1] = insert;
		}
	}
	
	/**
	 * Compute and return the average score.
	 * Precondition: the calling student list is not empty.
	 */
	public double averageScore() 
	{	
		if (numStudents > 0)
		{
			double sum = 0;

			// Computes the sum of all the marks within the cohort.
			for (int i = 0; i < numStudents; i++)
			{
				double index = list[i].getScore();
				sum += index;
			}

			// Returns the average.
			return sum/numStudents;		
		}
		else 
			return 0; // No students.
	}
	
	/**
	 * Return the score corresponding to aName in the calling student list.
	 * If the student is not in the list, return -1.
	 * Uses binary search algorithm.
	 * Precondition: the calling student list is assumed to be sorted by name.
	 * @param aName
	 * @return
	 */
	public double retrieveScore(String aName) 
	{
		int start = 0;
		int end = numStudents - 1;
		
		while (start <= end)
		{
			int middle = start + (end - start)/2;
			String nameAtMiddle = list[middle].getName();;
			
			// If the name at middle is the same as the name provided,
			// return the score of that student.
			if (nameAtMiddle.equals(aName)) 
				return list[middle].getScore();
			else if (nameAtMiddle.compareToIgnoreCase(aName) > 0)
				end = middle - 1;
			else
				start = middle + 1;
		}
		
		// Only if the name provided is not a student within the cohort.
		return -1;
	}
	
	/**
	 * Return a deep copy of calling object.
	 * @return meritList
	 */
	public StudentList deepCopy() 
	{
		// Initialises the deep copy object.
		StudentList meritList = new StudentList(numStudents);
		
		// Copies the current list values to the new list.
		for (int i = 0; i < numStudents; i++)
			meritList.list[i] = getAt(i);
		
		return meritList; // For meritSort.
	}
	
	/**
	 * Reverse sort list into merit order on score.
	 * Uses selection sort algorithm.
	 * 
	 * Question 2. Selection sort could be more efficient than some other
	 * sorting algorithms for the purpose intended. Do you agree and if so, why?
	 * Write 6 to 10 lines.
	 */
	
	/**
	 * As the list needs to be sorted in reverse order. It is typically
	 * better to use selection sort, than insertion sort. Because, 
	 * in the case of reverse sorting insertion sort would have a average time
	 * complexity of O(n^2), which is identical to selection sort.
	 * 
	 * Generally speaking, selection sort is much easier to implement for this
	 * problem than insertion sort; and is therefore easier to debug.
	 */
	public void meritSort() 
	{
		int max;
		
		for (int i = 0; i < numStudents; i++)
		{
			max = i;
			
			// Checks if the value of list at j is less than, the 
			// current maximum. 
			// If so it updates the maximum to be the element at j.
			for (int j = i + 1; j < numStudents; j++)
				if (list[j].getScore() > list[max].getScore())
					max = j;
			
			// Swap the value to its correct spot.
			if (max != i)
				swap(max, i);	
		}
	}
	
	/**
	 * Swaps the value of max into the correct position in the list.
	 * Replacing it's position with that of i. 
	 */ 
	public void swap(int max, int i)
	{
		// Stores one of the values to be swapped.
		Student toSwap = list[max];
		
		// Swaps those values.
		list[max] = list[i];
		list[i] = toSwap;
	}
	
	/**
	 * Sort list into alphabetical order by name - alternative method.
	 * This method should be based on binary insertion sort.
	 * Please see Assign 2 Description for brief description of this algorithm.
	 * 
	 * Question 3. Do you think it is worthwhile to implement this alternative?
	 * Why? Write 6 to 10 lines.
	 */
	
	/**
	 * Overall, I do not think it is worthwhile to implement binary insertion
	 * sort, as it can provides negligible benefit over regular insertion
	 * sort on a list of the required size. While it does change the best case
	 * time complexity to O(n*log(n)). it typically takes the worst case result,
	 * that being O(n^2). 
	 * Currently insertion sort takes a miniscule amount of time to sort a ~100
	 * sized entry list. Meaning the difference to using binary insertion sort
	 * does not necessarily matter to the client.
	 */
	public void sortByNameBIS()
	{
		int insertPosition = 0; // Where to insert up to.
		int j;
		int min = 0;
		int max = 0;
		int mid = 0;
		Student insert = new Student(); // Insertion element.
		
		for (int i = 1; i < numStudents; i++)
		{
			// Stores element to be inserted for insertion sort.
			insert = list[i];
			String temp = insert.getName();
			
			// Binary search variables, and resets them for every loop interation.
			min = 0;
			max = i;
			mid = 0;
			
			// Performs the binary search, and finds the insert position.
			binarySearch(min, max, mid, temp, insertPosition);
			
			// Insertion Sort.
			// Moves all other array indexes to create room for the insertion.
			for (j = i; j > insertPosition; j--)
				list[j] = list[j - 1];

			// Insert the element into the correct position.
			list[j] = insert;
		}
	}
	
	/**
	 * A modified binary search that is utilised by sortByNameBIS(),
	 * returns the point where the new element should be inserted into.
	 */
	public void binarySearch(int min, int max, int mid, String temp, int insertPosition)
	{
		// Binary Search method.
		while (min <= max)
		{
			// Sets the element to check.
			mid = (max + min)/2;
			String compareMid = list[mid].getName();

			// Searches for the lowest point, and eventually returns that point.
			if (temp.compareToIgnoreCase(compareMid) > 0)
				min = mid + 1;
			else if (temp.compareToIgnoreCase(compareMid) < 0)
				max = mid - 1;
			else 
			{
				insertPosition = mid;
				break; // Ends the loop.
			}
		}
	}
	
}
