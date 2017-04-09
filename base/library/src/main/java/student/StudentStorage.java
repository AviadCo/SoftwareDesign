package student;

import java.util.List;
import java.util.OptionalInt;

import il.ac.technion.cs.sd.grades.ext.LineStorage;

/** This class will handle the application storage requests. */
public class StudentStorage {

	public void addStudent(Student student) {
		LineStorage.appendLine(student.toString());
	}
	
	public Integer getNumberOfStudents() {
		try {
			return LineStorage.numberOfLines();
		} catch (InterruptedException e) {
			e.printStackTrace();
			
			throw new RuntimeException();
		}
	}
	
	public Student getStudentByLine(int line) {
		try {
			return Student.fromString(LineStorage.read(line));
		} catch (InterruptedException e) {
			e.printStackTrace();
			
			throw new RuntimeException();
		}
	}
	
	public void addMultipleStudents(List<Student> students) {
		students.stream().forEach(s -> addStudent(s));
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
