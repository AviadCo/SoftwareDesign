package il.ac.technion.ac.sd.app;

import static org.junit.Assert.*;

import java.util.Optional;
import java.util.OptionalInt;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import il.ac.technion.cs.sd.app.GradesInitializer;
import il.ac.technion.cs.sd.app.GradesReader;
import student.IStudentsDatabase;
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
	
	@Test
	public void parseFindExistingGrade()
	{    	
    	IStudentsDatabase studentDBMock = Mockito.mock(IStudentsDatabase.class);
    	
    	Mockito.when(studentDBMock.findStudentByID("123")).thenReturn(Optional.of(new Student("123",45)));
    	
    	GradesReader gradesReadTemp = new GradesReader(studentDBMock);
    	
    	OptionalInt result = gradesReadTemp.getGrade("123");
    	
    	assertTrue(result.isPresent());
    	assertEquals(45, result.getAsInt());
	}
	
	@Test
	public void parseFindNotExistingGrade()
	{    	
    	IStudentsDatabase studentDBMock = Mockito.mock(IStudentsDatabase.class);
    	
    	Mockito.when(studentDBMock.findStudentByID("123")).thenReturn(Optional.empty());
    	
    	GradesReader gradesReadTemp = new GradesReader(studentDBMock);
    	
    	OptionalInt result = gradesReadTemp.getGrade("123");
    	
    	assertFalse(result.isPresent());
	}
	
	@Test
	public void parseFindStudentByIDWithNull()
	{    	
    	IStudentsDatabase studentDBMock = Mockito.mock(IStudentsDatabase.class);
    	
    	Mockito.when(studentDBMock.findStudentByID(null)).thenReturn(Optional.of(new Student("123",45)));;
    	
    	GradesReader gradesReadTemp = new GradesReader(studentDBMock);
    	
    	OptionalInt result = gradesReadTemp.getGrade("123");
    	
    	assertFalse(result.isPresent());
    	
	}
}
