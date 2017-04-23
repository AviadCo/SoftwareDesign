package il.ac.technion.ac.sd.app;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import il.ac.technion.cs.sd.app.GradesInitializer;
import student.IStudentsDatabase;
import student.Student;

public class GradesInitializerTester {
		
	private GradesInitializer gradesInitializer;
	
	@Before
	public void setup() {
		gradesInitializer = new GradesInitializer();
	}
	
	@Test
	public void parseDataCheckListEmpty()
	{
    	List<Student> studentsList = gradesInitializer.parseData("");
    	
    	assertTrue(studentsList.isEmpty());
	}
	
	@Test
	public void parseDataCheckListWithoutDuplications()
	{
		String csvData = "";
		
		csvData += new Student("012", 100).toString() + System.lineSeparator();
		csvData += new Student("987", 11).toString() + System.lineSeparator();
		csvData += new Student("243", 21).toString() + System.lineSeparator();
		csvData += new Student("444", 33).toString() + System.lineSeparator();
		csvData += new Student("999999", 65).toString() + System.lineSeparator();		
		
    	List<Student> studentsList = gradesInitializer.parseData(csvData);
				
    	/* The list should contain: */
		assertTrue(studentsList.contains(new Student("012", 100)));
		assertTrue(studentsList.contains(new Student("987", 11)));
		assertTrue(studentsList.contains(new Student("243", 21)));
		assertTrue(studentsList.contains(new Student("444", 33)));
		assertTrue(studentsList.contains(new Student("999999", 65)));
		
    	/* The list shouldn't contain: */
		assertFalse(studentsList.contains(new Student("012", 52)));
		assertFalse(studentsList.contains(new Student("252", 42)));
		assertFalse(studentsList.contains(new Student("897", 132)));
	}
	
	@Test
	public void parseDataCheckListWithDuplications()
	{
		/* This test checks that only the last grade will be in the list */
		String csvData = "";
		
		csvData += new Student("012", 100).toString() + System.lineSeparator();
		csvData += new Student("987", 11).toString() + System.lineSeparator();
		csvData += new Student("243", 21).toString() + System.lineSeparator();
		csvData += new Student("444", 33).toString() + System.lineSeparator();
		csvData += new Student("999999", 65).toString() + System.lineSeparator();		
		csvData += new Student("987", 52).toString() + System.lineSeparator();
		csvData += new Student("555", 42).toString() + System.lineSeparator();
		csvData += new Student("243", 15).toString() + System.lineSeparator();
		csvData += new Student("987", 72).toString() + System.lineSeparator();
		
    	List<Student> studentsList = gradesInitializer.parseData(csvData);
				
		assertTrue(studentsList.contains(new Student("012", 100)));
		assertFalse(studentsList.contains(new Student("987", 11))); /* There is new grade for this student */
		assertFalse(studentsList.contains(new Student("243", 21))); /* There is new grade for this student */
		assertTrue(studentsList.contains(new Student("444", 33)));
		assertTrue(studentsList.contains(new Student("999999", 65)));
		assertFalse(studentsList.contains(new Student("987", 52))); /* There is new grade for this student */
		assertTrue(studentsList.contains(new Student("555", 42)));
		assertTrue(studentsList.contains(new Student("243", 15)));
		assertTrue(studentsList.contains(new Student("987", 72)));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSetupAddingItemToStorage()
	{
		/* This test checks that only the last grade will be in the list */
		String csvData = "";
		
		csvData += new Student("012", 100).toString() + System.lineSeparator();
		csvData += new Student("987", 11).toString() + System.lineSeparator();
		csvData += new Student("243", 21).toString() + System.lineSeparator();
		csvData += new Student("444", 33).toString() + System.lineSeparator();
		csvData += new Student("999999", 65).toString() + System.lineSeparator();		
		csvData += new Student("987", 52).toString() + System.lineSeparator();
		csvData += new Student("555", 42).toString() + System.lineSeparator();
		csvData += new Student("243", 15).toString() + System.lineSeparator();
		csvData += new Student("987", 72).toString() + System.lineSeparator();
				
    	IStudentsDatabase studentDBMock = Mockito.mock(IStudentsDatabase.class);
    	
    	GradesInitializer gradesInit = new GradesInitializer(studentDBMock);
    	gradesInit.setup(csvData);
    	
    	
    	Mockito.verify(studentDBMock, Mockito.atLeastOnce()).add((List<Student>) Mockito.any());
    	
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSetupNotCrashedFromNull()
	{
				
    	IStudentsDatabase studentDBMock = Mockito.mock(IStudentsDatabase.class);
    	
    	GradesInitializer gradesInit = new GradesInitializer(studentDBMock);
    	gradesInit.setup(null);
    	
    	Mockito.verify(studentDBMock, Mockito.never()).add((List<Student>) Mockito.any());
    	
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSetupNotCrashedFromEmptyString()
	{
				
    	IStudentsDatabase studentDBMock = Mockito.mock(IStudentsDatabase.class);
    	
    	GradesInitializer gradesInit = new GradesInitializer(studentDBMock);
    	gradesInit.setup(new String());
    	
	}
}
