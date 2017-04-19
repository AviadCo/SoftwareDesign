package student;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
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
	
	@Test (timeout = 500)
	public void findStudentByIDWithEmptyStorage()
	{
		assertEquals(studentStorage.findStudentByID(DEFAULT_ID), Optional.empty());
	}
	
	@Test (timeout = 500)
	public void findStudentByIDWhenIDExists()
	{
		List<Student> studentsList = new ArrayList<>();

		Student student = new Student(DEFAULT_ID, 100);
		
		studentsList.add(student);
		
		studentStorage.addMultipleStudents(studentsList);
		assertEquals(studentStorage.findStudentByID(DEFAULT_ID), Optional.of(student));
	}
	
	@Test (timeout = 500)
	public void findStudentByIDWhenIDDoesNotExist()
	{
		List<Student> studentsList = new ArrayList<>();
		Student student = new Student(DEFAULT_ID, 100);
		
		studentsList.add(student);
		
		studentStorage.addMultipleStudents(studentsList);
		assertEquals(studentStorage.findStudentByID(MISSING_ID), Optional.empty());
	}
	
	@Test (timeout = 500)
	public void findStudentByIDCheckRightGrade()
	{
		List<Student> studentsList = new ArrayList<>();
		Student student = new Student(DEFAULT_ID, 72);

		studentsList.add(student);
		
		studentStorage.addMultipleStudents(studentsList);
		assertEquals(studentStorage.findStudentByID(DEFAULT_ID).get().getGrade(), student.getGrade());
		assertNotEquals(studentStorage.findStudentByID(DEFAULT_ID).get().getGrade(), Integer.valueOf(50));
	}
	
	@Test (timeout = 1000)
	public void findStudentByIDMultipleIDs()
	{
		List<Student> studentsList = new ArrayList<>();
		
		studentsList.add(new Student("0", 100));
		studentsList.add(new Student("1", 68));
		studentsList.add(new Student("2", 5));
		studentsList.add(new Student("3", 100));
		studentsList.add(new Student("4", 89));

		studentStorage.addMultipleStudents(studentsList);
		
		assertEquals(studentStorage.findStudentByID("0"), Optional.of(new Student("0", 100)));
		assertEquals(studentStorage.findStudentByID("1"), Optional.of(new Student("1", 68)));
		assertEquals(studentStorage.findStudentByID("2"), Optional.of(new Student("2", 5)));
		assertEquals(studentStorage.findStudentByID("3"), Optional.of(new Student("3", 100)));
		assertEquals(studentStorage.findStudentByID("4"), Optional.of(new Student("4", 89)));
	}
	
	@Test (timeout = 2000)
	public void findStudentByIDSorting()
	{
		List<Student> studentsList = new ArrayList<>();
		
		studentsList.add(new Student("0", 100));
		studentsList.add(new Student("1", 68));
		studentsList.add(new Student("4", 89));
		studentsList.add(new Student("2", 5));
		studentsList.add(new Student("3", 100));
		studentsList.add(new Student("89", 52));
		studentsList.add(new Student("42", 33));
		studentsList.add(new Student("23", 11));
		studentsList.add(new Student("72", 3));
		studentsList.add(new Student("10484204", 96));


		studentStorage.addMultipleStudents(studentsList);
		
		assertEquals(studentStorage.findStudentByID("0"), Optional.of(new Student("0", 100)));
		assertEquals(studentStorage.findStudentByID("1"), Optional.of(new Student("1", 68)));
		assertEquals(studentStorage.findStudentByID("2"), Optional.of(new Student("2", 5)));
		assertEquals(studentStorage.findStudentByID("3"), Optional.of(new Student("3", 100)));
		assertEquals(studentStorage.findStudentByID("4"), Optional.of(new Student("4", 89)));
		assertEquals(studentStorage.findStudentByID("89"), Optional.of(new Student("89", 52)));
		assertEquals(studentStorage.findStudentByID("42"), Optional.of(new Student("42", 33)));
		assertEquals(studentStorage.findStudentByID("23"), Optional.of(new Student("23", 11)));
		assertEquals(studentStorage.findStudentByID("72"), Optional.of(new Student("72", 3)));
		assertEquals(studentStorage.findStudentByID("10484204"), Optional.of(new Student("10484204", 96)));
	}
	
	@Test (timeout = 500)
	public void getNumberOfStudents()
	{
		List<Student> studentsList = new ArrayList<>();
		
		studentsList.add(new Student("0", 100));
		studentsList.add(new Student("1", 68));
		studentsList.add(new Student("4", 89));
		studentsList.add(new Student("2", 5));
		studentsList.add(new Student("3", 100));
		
		studentStorage.addMultipleStudents(studentsList);
		
		assertEquals(studentStorage.getNumberOfStudents(), Integer.valueOf(5));
	}
}
