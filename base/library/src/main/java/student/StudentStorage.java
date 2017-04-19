package student;

import java.util.List;
import java.util.Optional;

import externelLibraryWrapper.IStorage;

/** This class will handle the application storage requests. */
public class StudentStorage {

	private IStorage iStorage;
	
	public StudentStorage(IStorage iStorage) {
		this.iStorage = iStorage;
	}
	
	private Student getStudentByLine(int line) {
		try {
			return new Student(iStorage.getItemByIndex(line));
		} catch (InterruptedException e) {
			e.printStackTrace();
			
			throw new RuntimeException();
		}
	}
	
	/**
	 * Add one student to the database
	 * 
	 * This function does not maintain the sort order achieved in 'addMultipleStudents'.
	 * 
	 * @param student - a student to add to the storage
	 */
	private void addStudent(Student student) {
		iStorage.addItemToStorage(student.toString());
	}
	
	/**
	 * Returns the number of students in database
	 * 
	 * @return - number of students in database
	 */
	public Integer getNumberOfStudents() {
		try {
			return iStorage.getNumberOfItems();
		} catch (InterruptedException e) {
			e.printStackTrace();
			
			throw new RuntimeException();
		}
	}
		
	/**
	 * Add multiple students at once
	 * 
	 * Do not add multiple students more than once since the database sorting won't be maintained
	 * 
	 * @param students - list of students to add
	 */
	public void addMultipleStudents(List<Student> students) {
		students.stream().sorted().forEach(s -> addStudent(s));
	}
	
	/**
	 * findStudentByID - returns Student by it's ID using binary search algorithm.
	 * 
	 * If database is not sorted (because of multiple uses of 'addMultipleStudents') the search won't work!
	 * 
	 * If Student dosen't exist returns Optional.empty().
	 *  */
	public Optional<Student> findStudentByID(String ID) {
		Integer numberOfLines = getNumberOfStudents();
		Integer lowLine, highLine;

		if (numberOfLines == 0) {
			return Optional.empty();
		}
		
		lowLine = 0;
		highLine = numberOfLines - 1;
				
		while (lowLine <= highLine) {
			int currentLine = (lowLine + highLine) / 2;
			Student currentStudent = getStudentByLine(currentLine);
			int compareResult = currentStudent.getID().compareTo(ID);
			
			if (compareResult == 0) {
				return Optional.of(currentStudent);
			} else if (compareResult < 0) {
				lowLine = currentLine + 1;
			} else {
				highLine = currentLine - 1;
			}
		}
		
		return Optional.empty();
	}
}
