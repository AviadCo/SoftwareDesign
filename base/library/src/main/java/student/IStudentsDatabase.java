package student;

import java.util.List;
import java.util.Optional;

/**
 * This is interface represent Storage for students.
 * 
 * @author Noam
 *
 */
public interface IStudentsDatabase {

	/**
	 * Add one student to the database
	 * 
	 * This function does not maintain the sort order achieved in 'addMultipleStudents'.
	 * 
	 * @param student - a student to add to the storage
	 */
	void add(Student student);

	/**
	 * Returns the number of students in database
	 * 
	 * @return - number of students in database
	 */
	Integer getNumberOfStudents();

	/**
	 * Add multiple students at once
	 * 
	 * Do not add multiple students more than once since the database sorting won't be maintained
	 * 
	 * @param students - list of students to add
	 */
	void add(List<Student> students);

	/**
	 * findStudentByID - returns Student by it's ID using binary search algorithm.
	 * 
	 * If database is not sorted (because of multiple uses of 'addMultipleStudents') the search won't work!
	 * 
	 * If Student dosen't exist returns Optional.empty().
	 *  */
	Optional<Student> findStudentByID(String ID);

}