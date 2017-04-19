package il.ac.technion.cs.sd.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import externelLibraryWrapper.BasicStorage;
import student.Student;
import student.StudentStorage;

/** This class will be instantiated once per test. */
public class GradesInitializer {
  /**
   * Saves the csvData persistently, so that it could be run using GradesRunner.
   * The format of each line of the data is $id,$grade.
   */
	
	public List<Student> parseData(String csvData) {
	  	Map<String, Boolean> seen = new HashMap<String, Boolean>();
	
	  	if (csvData.isEmpty()) {
	  		/* stream dosen't handle empty array well */
	  		return new ArrayList<Student>();
	  	}
	  	
	  	/* Removing duplications of students and stores only the last student data */	  	
		List<Student> studentsList = Arrays.asList(csvData.split(System.lineSeparator())).stream().map( s-> new Student(s)).collect(Collectors.toList());
		List<Student> reverseStudentsList = Lists.reverse(studentsList);
		return reverseStudentsList.stream()
				   .filter(s -> seen.putIfAbsent(s.getID(), Boolean.TRUE) == null)
			       .collect(Collectors.toList());
	}
	
	public void setup(String csvData) {
		new StudentStorage(new BasicStorage()).addMultipleStudents(parseData(csvData));
	}
}
