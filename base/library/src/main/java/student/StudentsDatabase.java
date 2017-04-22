package student;

import java.util.List;
import java.util.Optional;

import externelLibraryWrapper.IStorage;

/** This class will handle the application storage requests. */
public class StudentsDatabase implements IStudentsDatabase {

	private IStorage iStorage;
	
	public StudentsDatabase(IStorage iStorage) {
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
	
	/* (non-Javadoc)
	 * @see student.IStudentsDatabase#addStudent(student.Student)
	 */
	@Override
	public void add(Student student) {
		iStorage.addItemToStorage(student.toString());
	}
	
	/* (non-Javadoc)
	 * @see student.IStudentsDatabase#getNumberOfStudents()
	 */
	@Override
	public Integer getNumberOfStudents() {
		try {
			return iStorage.getNumberOfItems();
		} catch (InterruptedException e) {
			e.printStackTrace();
			
			throw new RuntimeException();
		}
	}
		
	/* (non-Javadoc)
	 * @see student.IStudentsDatabase#addMultipleStudents(java.util.List)
	 */
	@Override
	public void add(List<Student> students) {
		students.stream().sorted().forEach(s -> add(s));
	}
	
	/* (non-Javadoc)
	 * @see student.IStudentsDatabase#findStudentByID(java.lang.String)
	 */
	@Override
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
