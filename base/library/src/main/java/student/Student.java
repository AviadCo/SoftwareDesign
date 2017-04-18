package student;

/* This class will store the data of a Student. */
public class Student implements Comparable<Student> {
	private String ID;
	private Integer grade;
	
	//TODO try to remove this constructor
	public Student() {
		this.ID = "";
		this.grade = 0;
	}
	
	public Student(String student) {
		String studentArgs[] = student.split(",");
		
		this.ID = studentArgs[0];
		this.grade = Integer.valueOf(studentArgs[1]);
	}
	
	public Student(String iD, Integer grade) {
		this.ID = iD;
		this.grade = grade;
	}

	@Override
	public String toString() {
		return getID() + "," + getGrade();
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) {
	    	return false;
	    }
	    
	    if (other == this) {
	    	return true;
	    } 
	    
	    if (!(other instanceof Student)) {
	    	return false;
	    }
	    
	    Student otherStudent = (Student) other;
	    
	    return otherStudent.getID().equals(ID) && otherStudent.getGrade().equals(grade);
	}
	
	@Override
	public int hashCode() {
		return ID.hashCode();
	}

	@Override
	public int compareTo(Student other) {
		return ID.compareTo(other.getID());
	}
}
