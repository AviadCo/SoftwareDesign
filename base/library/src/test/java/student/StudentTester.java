package student;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class StudentTester {
	static Student student;
	
	@BeforeClass
	public static void initialize(){
		student = new Student();
		
		student.setID("1234");
		student.setGrade(50);
	}
	
	@Test
	public void StudentsNotEqualstest() {	
		assertFalse(student.equals(50));
		assertFalse(student.equals("1234"));
		assertFalse(student.equals(new Student("555",78)));
		assertFalse(student.equals(new Student("1234",78)));
		assertFalse(student.equals(new Student("555",50)));
	}
	
	@Test
	public void StudentsEqualstest() {
		assertTrue(student.equals(new Student("1234",50)));
	}
	
	@Test
	public void CreateStudentFromStringtest() {
		Student parsedStduent = new Student("789,80");
		
		assertEquals(new Integer(80), parsedStduent.getGrade());
		assertEquals("789", parsedStduent.getID());
	}
	
	@Test
	public void CreateStudentToStringtest() {
		assertEquals("1234,50", student.toString());
	}

}
