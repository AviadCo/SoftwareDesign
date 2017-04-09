package student;

import java.util.OptionalInt;

/* This class will store the data of a Student. */
public class Student {
	private String ID;
	private OptionalInt grade;
	
	public Student() {
		this.ID = "";
		this.grade = OptionalInt.empty();
	}
	
	public Student(String iD, OptionalInt grade) {
		this.ID = iD;
		this.grade = grade;
	}

	public static Student fromString(String student) {
		String studentArgs[] = student.split(",");
		
		return new Student(studentArgs[0], OptionalInt.of(Integer.valueOf(studentArgs[1])));
	}
	
	public String toString(Student student) {
		return student.getID() + "," + student.getGrade();
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public OptionalInt getGrade() {
		return grade;
	}
	public void setGrade(OptionalInt grade) {
		this.grade = grade;
	}
}
