package il.ac.technion.ac.sd.app;

import static org.junit.Assert.*;

import java.util.Optional;
import java.util.OptionalInt;

import org.junit.Before;
import org.junit.Test;

import il.ac.technion.cs.sd.app.GradesReader;
import student.Student;

public class GradesReaderTester {

	private GradesReader gradesReader;
	
	@Before
	public void setup() {
		gradesReader = new GradesReader();
	}
	
	@Test
	public void parseStudentEmpty()
	{    	
    	assertEquals(gradesReader.parseStudentGrade(Optional.empty()), OptionalInt.empty());
	}
	
	@Test
	public void parseStudentWithGrade()
	{    	
    	assertEquals(gradesReader.parseStudentGrade(Optional.of(new Student("123", 55))), OptionalInt.of(55));
    	assertEquals(gradesReader.parseStudentGrade(Optional.of(new Student("123", 63))), OptionalInt.of(63));
    	assertEquals(gradesReader.parseStudentGrade(Optional.of(new Student("264", 15))), OptionalInt.of(15));
	}
}
