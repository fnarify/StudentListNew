package comp125;

import static org.junit.Assert.*;
import org.junit.Test;

public class StudentListTest {
	@Test
	public void testStudentList() {
		StudentList myList = new StudentList(3);
		Student student0 = new Student("Peterson", 66.0);
		Student student1 = new Student("Jones", 75.0);
		Student student2 = new Student("Adams", 71.5);
		myList.setAt(0, student0);
		myList.setAt(1, student1);
		myList.setAt(2, student2);
		assertTrue(myList.getAt(0).equals(student0));
		assertTrue(myList.getAt(1).equals(student1));
		assertTrue(myList.getAt(2).equals(student2));
	}
	
	@Test
	public void testAverageScore() {
		StudentList myList = new StudentList(3);
		Student student0 = new Student("Peterson", 66.0);
		Student student1 = new Student("Jones", 75.0);
		Student student2 = new Student("Adams", 72.0);
		myList.setAt(0, student0);
		myList.setAt(1, student1);
		myList.setAt(2, student2);
		assertEquals(71.0, myList.averageScore(), 0.01);
	}
	
	@Test
	public void testSortByName() {
		StudentList myList = new StudentList(3);
		Student student0 = new Student("Peterson", 66.0);
		Student student1 = new Student("Jones", 75.0);
		Student student2 = new Student("Adams", 72.0);
		myList.setAt(0, student0);
		myList.setAt(1, student1);
		myList.setAt(2, student2);
		myList.sortByName();
		assertTrue(myList.getAt(0).equals(student2));
		assertTrue(myList.getAt(1).equals(student1));
		assertTrue(myList.getAt(2).equals(student0));
	}
	
	@Test
	public void testRetrieveScore() {
		StudentList myList = new StudentList(3);
		Student student0 = new Student("Peterson", 66.0);
		Student student1 = new Student("Jones", 75.0);
		Student student2 = new Student("Adams", 72.0);
		myList.setAt(0, student0);
		myList.setAt(1, student1);
		myList.setAt(2, student2);
		myList.sortByName();
		assertEquals(72.0, myList.retrieveScore("Adams"), 0.01);
		assertEquals(75.0, myList.retrieveScore("Jones"), 0.01);
		assertEquals(66.0, myList.retrieveScore("Peterson"), 0.01);
	}
	
	@Test
	public void testMeritSort() {
		StudentList myList = new StudentList(3);
		Student student0 = new Student("Peterson", 66.0);
		Student student1 = new Student("Jones", 75.0);
		Student student2 = new Student("Adams", 72.0);
		myList.setAt(0, student0);
		myList.setAt(1, student1);
		myList.setAt(2, student2);
		myList.meritSort();
		assertTrue(myList.getAt(0).equals(student1));
		assertTrue(myList.getAt(1).equals(student2));
		assertTrue(myList.getAt(2).equals(student0));
	}

}
