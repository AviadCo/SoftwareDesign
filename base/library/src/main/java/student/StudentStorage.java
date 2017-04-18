package student;

import java.util.List;
import java.util.OptionalInt;

import externelLibraryWrapper.IStorage;

/** This class will handle the application storage requests. */
public class StudentStorage {

	private IStorage iStorage;
	
	public StudentStorage(IStorage iStorage) {
		this.iStorage = iStorage;
	}
	
	public void addStudent(Student student) {
		iStorage.addItemToStorage(student.toString());
	}
	
	public Integer getNumberOfStudents() {
		try {
			return iStorage.getNumberOfItems();
		} catch (InterruptedException e) {
			e.printStackTrace();
			
			throw new RuntimeException();
		}
	}
	
	public Student getStudentByLine(int line) {
		try {
			return Student.fromString(iStorage.getItemByIndex(line));
		} catch (InterruptedException e) {
			e.printStackTrace();
			
			throw new RuntimeException();
		}
	}
	
	public void addMultipleStudents(List<Student> students) {
		students.stream().sorted().forEach(s -> addStudent(s));
	}
	
	/**
	 * findStudentByID - returns Student by it's ID using binary search algorithm.
	 * 
	 * If Student dosen't exist it's grade will me OptionalInt.empty().
	 *  */
	public Student findStudentByID(String ID) {
		Integer numberOfLines = getNumberOfStudents();
		Integer lowLine, highLine;

		lowLine = 0;
		highLine = numberOfLines - 1;
		
		while (lowLine <= highLine) {
			int currentLine = (lowLine + highLine) / 2;
			Student currentStudent = getStudentByLine(currentLine);
			int compareResult = currentStudent.getID().compareTo(ID);
			
			if (compareResult == 0) {
				return currentStudent;
			} else if (compareResult < 0) {
				lowLine = currentLine + 1;
			} else {
				highLine = currentLine - 1;
			}
		}
		
		return new Student(ID, OptionalInt.empty());
	}
}
