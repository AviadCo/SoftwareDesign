package student;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import externelLibraryWrapper.IStorage;
import externelLibraryWrapper.MapBasedStorage;

@RunWith(MockitoJUnitRunner.class)
public class StudentStorageTester {
	private StudentStorage studentStorage;

	private final String DEFAULT_ID = "01234";	
	private final String MISSING_ID = "00000";

	@Mock
	private IStorage iStorage;
	
	@Before
	public void setup() {
		iStorage = Mockito.spy(new MapBasedStorage());
		studentStorage = new StudentStorage(iStorage);
	}
	
	@Test
	public void findStudentByIDWithEmptyStorage()
	{
		assertEquals(studentStorage.findStudentByID(DEFAULT_ID), Optional.empty());
	}
	
	@Test
	public void findStudentByIDWhenIDExists()
	{
		Student student= new Student(DEFAULT_ID, 100);
		
		studentStorage.addStudent(student);
		assertEquals(studentStorage.findStudentByID(DEFAULT_ID), Optional.of(student));
	}
	
	@Test
	public void findStudentByIDWhenIDDoesNotExist()
	{
		Student student= new Student(DEFAULT_ID, 100);
		
		studentStorage.addStudent(student);
		assertEquals(studentStorage.findStudentByID(MISSING_ID), Optional.empty());
	}
}
