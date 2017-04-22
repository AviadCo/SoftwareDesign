package il.ac.technion.cs.sd.app;

import java.util.Optional;
import java.util.OptionalInt;

import externelLibraryWrapper.BasicStorage;
import student.IStudentsDatabase;
import student.Student;
import student.StudentsDatabase;

/**
 * This class will only be instantiated after
 * {@link il.ac.technion.cs.sd.app.GradesInitializer#setup(java.lang.String) has been called}.
 */
public class GradesReader {
  /** Returns the grade associated with the ID, or empty. */

	private IStudentsDatabase studentsDB;
	
	public GradesReader() {
		this.studentsDB = new StudentsDatabase(new BasicStorage());
	}
	
	public GradesReader(IStudentsDatabase studentsDB) {
		this.studentsDB = studentsDB;
	}

	public OptionalInt parseStudentGrade(Optional<Student> student)
	{
		return student.isPresent() ? OptionalInt.of(student.get().getGrade()) :
			OptionalInt.empty();
	}
	
	public OptionalInt getGrade(String id) {
		Optional<Student> student = studentsDB.findStudentByID(id);
	  
		return parseStudentGrade(student);
	}
}
